package com.example.meteoappproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.example.meteoappproject.ThreadMeteo

class DataListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_meteo, container, false)
        val meteoDataService = MeteoDataService()
        val searchView = view.findViewById<SearchView>(R.id.search_bar)
        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        searchUserData()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(city: String?): Boolean {
                // Lancer la requête pour rechercher la ville ici
                if (city != null && userEmail != null) {
                    meteoDataService.addCityToUserList(city, userEmail) { ok ->
                        if (ok) {
                            Toast.makeText(context, "City added", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "City already added", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return view
    }

    private fun searchUserData() {
        val userEmail = FirebaseAuth.getInstance().currentUser?.email

        if (userEmail !== null) {
            val getMeteoTask = ThreadMeteo(userEmail) { jsonObject ->
                Toast.makeText(context, "meteoData: $jsonObject", Toast.LENGTH_SHORT).show()
//                        for (data in jsonObject) {
//                            val button = TextView(context)
//                            button.text = data.name
//                            button.setTextColor(Color.WHITE)
//                            button.textSize = 20F
//                            button.setOnClickListener {
//                                val detailledDataFragment = DetailledInformationFragment()
//                                val bundle = Bundle()
//                                bundle.putString("name", data.name)
//                                bundle.putString("country", data.country)
//                                bundle.putString("iconUrl", data.iconUrl)
//                                bundle.putString("maxTemp", data.maxTemp)
//                                bundle.putString("minTemp", data.minTemp)
//                                detailledDataFragment.arguments = bundle
//                                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                                transaction?.replace(R.id.liste_meteo_fragment, detailledDataFragment)
//                                transaction?.commit()
//                            }
//
//                            view?.findViewById<LinearLayout>(R.id.liste_meteo_fragment)?.addView(button)
//                        }
                    }
            getMeteoTask.execute()

        }
    }
}

