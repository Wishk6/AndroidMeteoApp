package com.example.meteoappproject

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MeteoDataService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    // fait une focntion qui poermet d'ajouter un objet meteo dans noter base de donnée firebase
    // le model de donnné e
}