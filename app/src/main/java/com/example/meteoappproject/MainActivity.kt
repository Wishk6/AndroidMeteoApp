package com.example.meteoappproject

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var meteoService: MeteoDataService

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
        // Faire un service pour les 'permissionsTcheck' dans un service pour pouvoir vérifier si l'utilisateur a toujours les permissions autorisés ou pas
        // faire un appel à MeteoDataService.getMeteoByCoord() pour récupérer la data de la position actuelle grâce a Location.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_sign_in_fragment, DataListFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun onLoginFailed() {
        val toast = Toast.makeText(this, "login failed", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 500)
        toast.show()
    }

    fun onSignUpSuccess() {

//     TODO :inutile pour l'instant : Dabord Régler Problème de coordonnées en convertissant
//
//        meteoService = MeteoDataService()
//        val actualPositionData = meteoService.getTemperature(location.latitude, location.longitude)
//
//        val meteoDataModel = MeteoDataModel(
//            actualPositionData.country,
//            actualPositionData.iconUrl,
//            actualPositionData.maxTemp,
//            actualPositionData.minTemp,
//            actualPositionData.name,
//            ""
//        )
        super.onBackPressed()
    }

    fun onSignUpFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

        // verify which fragment is visible
        val fragmentSignIn = supportFragmentManager.findFragmentById(R.id.login_sign_in_fragment)
        val fragmentSignUp = supportFragmentManager.findFragmentById(R.id.signup_fragment)
        val fragmentMeteo = supportFragmentManager.findFragmentById(R.id.liste_meteo_fragment)

        if (fragmentSignIn !== null && fragmentSignUp == null || fragmentMeteo !== null && fragmentSignUp == null) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit Meteo Radar")
            builder.setMessage("Are you sure you want to exit ?")
            builder.setPositiveButton("Yes") { _, _ ->
                finish()
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else {
            super.onBackPressed()
        }
    }
}

