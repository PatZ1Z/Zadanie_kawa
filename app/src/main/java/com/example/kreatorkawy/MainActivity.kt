package com.example.kreatorkawy

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Image = findViewById<ImageView>(R.id.kawaImage)
        var coffe: Int
        var ImageList = listOf(
            R.drawable.latte,
            R.drawable.capuccino,
            R.drawable.espresso
        )
        var typeOfCoffe: String

        val radioGroup = findViewById<RadioGroup>(R.id.RadioGroup)
        radioGroup.setOnCheckedChangeListener { _, chechedId ->
            val radioButton = findViewById<RadioButton>(chechedId)
            typeOfCoffe = radioButton.text.toString()
            if(typeOfCoffe == "Cappuccino") {coffe =  1}
            else if (typeOfCoffe == "Espresso") {coffe =  2}
            else {coffe =  0}
                Image.setImageResource(ImageList[coffe])
    }

        val ilosc = findViewById<TextView>(R.id.ilosc_label)
        val SeekBar = findViewById<SeekBar>(R.id.ilosc_SeekBar)
        SeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ilosc.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        val submit = findViewById<Button>(R.id.submitButton)
        val latte = findViewById<RadioButton>(R.id.latte)
        val cappuccino = findViewById<RadioButton>(R.id.cappuccino)
        val mleko = findViewById<CheckBox>(R.id.mleko)
        val cukier = findViewById<CheckBox>(R.id.cukier)
        val Espresso = findViewById<RadioButton>(R.id.espresso)
        submit.setOnClickListener {
            Toast.makeText(this@MainActivity, "Twoje zamówienie: \n " +
                    (if (latte.isChecked) "Latte"
                    else if (cappuccino.isChecked) "Cappuccino"
                    else if(Espresso.isChecked) "Espresso"
                    else "")
                    +
                    (if (mleko.isChecked and cukier.isChecked) " z mlekiem i cukrem"
                    else if (mleko.isChecked) " z mlekiem"
                    else if (cukier.isChecked) " z cukrem"

                    else "") + "\n w ilosci:" +
                    ilosc.text.toString(), Toast.LENGTH_LONG).show()
        }
    }
}