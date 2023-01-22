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
    private lateinit var userService: UserService

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        email = view.findViewById(R.id.signup_email)
        password = view.findViewById(R.id.signup_password)
        signUpButton = view.findViewById(R.id.signup_button)
        userService = UserService()

        signUpButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
            checkLocationPermissions(emailText,passwordText)
            }
        }
        return view
    }

    private fun checkLocationPermissions( emailText: String, passwordText: String) {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Toast.makeText(context, "Please, go to app settings to change permissions :)", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Else check loca", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
                getLocation(emailText,passwordText)
            }
        } else {
            // Permission has already been granted
            getLocation(emailText,passwordText)
        }
    }


    private fun getLocation(emailText: String, passwordText: String) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Permission denied, retry", Toast.LENGTH_SHORT).show()
            return
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // si on récupère la localisation, on créer notre user, et on ajoute a notre collection firestore les données de localisation liés à l'utilisateur
                    if (location != null) {
                     createUser(emailText,passwordText,location)
                    } else {
                        Toast.makeText(context, "Error while trying to add user", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "Failed to get location, please retry", Toast.LENGTH_SHORT).show()
                }
        }

    }

    fun createUser(emailText: String, passwordText: String, location: Location) {

    }

}