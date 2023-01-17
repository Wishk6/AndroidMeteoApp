package com.example.meteoappproject

import android.annotation.SuppressLint
import android.os.Bundle;
import android.util.Log
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

class WeatherListFragment : Fragment() {

    private lateinit var location: String
    private lateinit var temperature: TextView
    private lateinit var forecast: TextView
    private lateinit var icon: ImageView
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestQueue = Volley.newRequestQueue(context)
    }

//    @SuppressLint("SetTextI18n")
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_weather_list, container, false)
//        temperature = view.findViewById(R.id.temperature)
//        forecast = view.findViewById(R.id.forecast)
//        icon = view.findViewById(R.id.icon)
//
//        // Récupération de l'emplacement de l'utilisateur à partir de la BDD Firestore
//        // ...
//
//        // Récupération des données météo à partir de l'API
//        val url =
//            "https://prevision-meteo.ch/services/json/lat=${location.latitude}lng=${location.longitude}"
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
//            val temperature = response.getInt("temperature")
//            val forecast = response.getString("forecast")
//            val iconUrl = response.getString("icon_url")
//            this.temperature.text = "$temperature °C"
//            this.forecast.text = forecast
//            Picasso.get().load(iconUrl).into(icon)
//        }, { error ->
//            Log.e("WeatherFragment", "Error while fetching weather data: $error")
//        })
//        requestQueue.add(jsonObjectRequest)
//        return view
//    }
}