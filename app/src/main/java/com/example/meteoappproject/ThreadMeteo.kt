package com.example.meteoappproject

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.net.URL

class ThreadMeteo(private val name: String, private val callback: (JSONObject) -> Unit) : AsyncTask<Void, Void, JSONObject?>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Void?): JSONObject? {
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

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: JSONObject?) {
        super.onPostExecute(result)
        if (result != null) {
            callback(result)
        }
    }
}