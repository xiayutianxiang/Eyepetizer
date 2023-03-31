package com.pax.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temperatureView = findViewById<TemperatureView>(R.id.temperatureView)
        /*temperatureView.setupTemperature(60f)
        temperatureView.startTimerAnim()*/
    }
}