package com.example.meteoappproject

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.meteoappproject.models.MeteoDataModel
import org.json.JSONObject
import java.net.URL

class MeteoDataService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // utiles uniquement pour la position actuelle, car l'api ne permet pas de récupérer le nom d'une ville uniquement avec des coordonnées
    private fun getMeteoByCoord(lat: Double, lng: Double): JSONObject? {
        val url = "https://www.prevision-meteo.ch/services/json/lat=$lat&lng=$lng"
        var jsonObject: JSONObject? = null
        try {
            val jsonString = URL(url).readText()
            jsonObject = JSONObject(jsonString)
            Log.d("MeteoDataService", "jsonObject: $jsonObject")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonObject
    }

    fun getTemperature(lat: Double, lng: Double): MeteoDataModel {
        val json = getMeteoByCoord(lat, lng)
        val minTemp: Double = json?.getJSONObject("fcst_day_0")?.getDouble("tmin") ?: 0.0
        val maxTemp: Double = json?.getJSONObject("fcst_day_0")?.getDouble("tmax") ?: 0.0
        val iconUrl: String = json?.getJSONObject("current_condition")?.getString("icon") ?: ""

        return MeteoDataModel(
            country = "", iconUrl, maxTemp.toString(), minTemp.toString(), name = "", userEmail = ""
        )
    }

    // dans ces fonction on récupère le nom de la ville en plus des coordonnées, l'url de l'image et le pays
    private fun getMeteoByName(name: String): JSONObject? {
        val url = "https://www.prevision-meteo.ch/services/json/$name"
        var jsonObject: JSONObject? = null
        try {
            val jsonString = URL(url).readText()
            jsonObject = JSONObject(jsonString)
            Log.d("MeteoDataService", "jsonObject: $jsonObject")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonObject
    }

    fun getDataFromCity(city: String, userEmail: String): MeteoDataModel {
        val json = getMeteoByName(city)
        val name: String = json?.getJSONObject("city_info")?.getString("name") ?: ""
        val country: String = json?.getJSONObject("city_info")?.getString("country") ?: ""
        val iconUrl: String = json?.getJSONObject("current_condition")?.getString("icon") ?: ""
        val maxTemp: Double = json?.getJSONObject("fcst_day_0")?.getDouble("tmin") ?: 0.0
        val minTemp: Double = json?.getJSONObject("fcst_day_0")?.getDouble("tmax") ?: 0.0
        return MeteoDataModel(
            country, iconUrl, maxTemp.toString(), minTemp.toString(), name, userEmail
        )
    }

}