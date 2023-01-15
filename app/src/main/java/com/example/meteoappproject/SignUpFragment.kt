package com.example.meteoappproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var username: EditText
    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        email = view.findViewById(R.id.signup_email)
        password = view.findViewById(R.id.signup_password)
        username = view.findViewById(R.id.signup_username)
        signUpButton = view.findViewById(R.id.signup_button)

        signUpButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val usernameText = username.text.toString()

            if (emailText.isEmpty() || passwordText.isEmpty() || usernameText.isEmpty()) {
                Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Toast.makeText(
                    context,
                    "Sign up success$emailText $passwordText $usernameText",
                    Toast.LENGTH_SHORT
                ).show()
                // TODO: Implement sign up with Firebase
            }
        }

        return view
    }

}