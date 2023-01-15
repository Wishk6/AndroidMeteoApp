package com.example.meteoappproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


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

    fun onLoginSuccess() {
        // Navigation vers la section suivante de l'application ou afficher un message de bienvenue
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
    }

    fun onLoginFailed() {
        // Afficher un message d'erreur à l'utilisateur
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
    }
}

