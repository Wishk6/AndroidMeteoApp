package com.example.meteoappproject

import com.google.firebase.auth.FirebaseAuth

class UserService {

    private val mAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(
        email: String, password: String, callback: (Boolean) -> Unit
    ) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
    }
}
