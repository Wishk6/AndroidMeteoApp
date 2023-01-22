package com.example.meteoappproject

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUpButton: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        // check a l'init
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        email = view.findViewById(R.id.signup_email)
        password = view.findViewById(R.id.signup_password)
        signUpButton = view.findViewById(R.id.signup_button)
        checkLocationPermissions();

        signUpButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                checkLocationPermissions(emailText, passwordText)
            }
        }
        return view
    }

    private fun checkLocationPermissions(emailText: String = "", passwordText: String = "") {

        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                (activity as MainActivity).onSignUpFailed(" Si vous voulez créer un compte, allez dans les paramètres de l'application pour autoriser l'accès à la localisation")
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
                return
            }
        } else {
            // Permission has already been granted
            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                getLocation(emailText, passwordText)
            }
        }
    }

    private fun getLocation(emailText: String, passwordText: String) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // on passe location en paramètre pour pouvoir l'utiliser dans la fonction onLoginSuccess
                    createUser(emailText, passwordText, location)
                } else {
                    (activity as MainActivity).onSignUpFailed("Impossible de récupérer votre localisation")
                }
            }.addOnFailureListener {
                (activity as MainActivity).onSignUpFailed("Impossible de récupérer la localisation depuis le GPS")
            }
        }
    }

    // pas dans un service car utilisée que dans cette classe
    private fun createUser(emailText: String, passwordText: String, location: Location) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
//                (activity as MainActivity).onSignUpSuccess(location)
                (activity as MainActivity).onSignUpSuccess()
            }.addOnFailureListener {
                (activity as MainActivity).onSignUpFailed("Failed to create user: ${it.message}")
            }
    }

}