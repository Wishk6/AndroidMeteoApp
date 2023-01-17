package com.example.meteoappproject

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoginFragment();
        supportActionBar?.hide();
        setContentView(R.layout.activity_main)
    }

    private fun showLoginFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.login_sign_in_fragment, LoginFragment())
        fragmentTransaction.commit()
    }

    fun onLoginSuccess(uid: FirebaseUser?) {
        // Navigation vers la section suivante de l'application ou afficher un message de bienvenue
        Toast.makeText(this, "login success $uid", Toast.LENGTH_SHORT).show()
    }

    fun onLoginFailed() {
        val toast = Toast.makeText(
            applicationContext,
            "Login Failed", Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 500)
        toast.show()
    }

    fun onSignUpSuccess(uid: String?) {
        // Handle sign up success
        // You can start a new activity, display a message, or do something else
        Toast.makeText(this, "Hello $uid", Toast.LENGTH_SHORT).show();

    }

    fun onSignUpFailed() {
        // Handle sign up failure
        // You can display an error message or do something else
        Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
    }

}

