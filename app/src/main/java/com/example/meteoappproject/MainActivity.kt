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
        //TODO  Navigation vers la section suivante de l'application ou afficher un message de bienvenue
        Toast.makeText(this, "login success $uid", Toast.LENGTH_SHORT).show()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.liste_meteo_fragment, ListeMeteoFragment())
        fragmentTransaction.commit()
    }

    fun onLoginFailed() {
        val toast = Toast.makeText(
            applicationContext,
            "Login Failed", Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 500)
        toast.show()
    }

    fun onSignUpSuccess() {
        Toast.makeText(this, "Sign Up success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    fun onSignUpFailed() {
        Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()

    }

}

