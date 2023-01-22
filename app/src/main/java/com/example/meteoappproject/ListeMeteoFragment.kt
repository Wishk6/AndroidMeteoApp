package com.example.meteoappproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListeMeteoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_meteo, container, false)
        val buttonList = view.findViewById<LinearLayout>(R.id.liste_meteo_fragment)

        val buttons = listOf(
            ButtonData("Button 1", "Titre 1", "Sous-titre 1"),
            ButtonData("Button 2", "Titre 2", "Sous-titre 2"),
            ButtonData("Button 3", "Titre 3", "Sous-titre 3")
        )

        for (buttonData in buttons) {
            val buttonContainer = LinearLayout(context)
            buttonContainer.orientation = LinearLayout.VERTICAL
            buttonContainer.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            //buttonContainer.setBackgroundResource(R.drawable.ic_image)

            val buttonTitle = TextView(context)
            buttonTitle.text = buttonData.title
            buttonTitle.setTextColor(Color.WHITE)
            buttonTitle.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val buttonSubtitle = TextView(context)
            buttonSubtitle.text = buttonData.subtitle
            buttonSubtitle.setTextColor(Color.WHITE)
            buttonSubtitle.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            buttonContainer.addView(buttonTitle)
            buttonContainer.addView(buttonSubtitle)
            buttonList.addView(buttonContainer)
        }

        return view
    }
}

data class ButtonData(val text: String, val title: String, val subtitle: String)
