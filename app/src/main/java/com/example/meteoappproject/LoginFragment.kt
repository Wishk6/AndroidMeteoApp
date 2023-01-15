package com.example.meteoappproject

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment

import com.google.firebase.auth.FirebaseAuth;

class LoginFragment : Fragment() {

    private lateinit var mEmailField: EditText
    private lateinit var mPasswordField: EditText
    private lateinit var mLoginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_sign_in, container, false)

        mEmailField = view.findViewById(R.id.email_field)
        mPasswordField = view.findViewById(R.id.password_field)
        mLoginButton = view.findViewById(R.id.login_button)
        signUpButton = view.findViewById(R.id.signup_button)
        mAuth = FirebaseAuth.getInstance()

        // login button
        mLoginButton.setOnClickListener {
            val email = mEmailField.text.toString()
            val password = mPasswordField.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        (activity as MainActivity).onLoginSuccess()
                    } else {
                        (activity as MainActivity).onLoginFailed()
                    }
                }
            }
        }

        // sign up button
        signUpButton.setOnClickListener {
            val signUpFragment = SignUpFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.signup_fragment, signUpFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }
}