package com.example.fiadoapptest.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fiadoapptest.core.LocationService
import com.example.fiadoapptest.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timerTask

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val locationService = LocationService(this)
        if (!locationService.isLocationEnabled()) {
            Toast.makeText(this, "Por favor enciende el GPS", Toast.LENGTH_SHORT).show()
        } else {
            val fusedLocationProviderClient = locationService.getLastKnowLocation()
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                val readableLocation = locationService.getCityName(location)
                binding.tvLocation.text = readableLocation
                Timer().schedule(timerTask {
                    val intent = Intent(this@MainActivity, CryptoTableActivity::class.java)
                    intent.putExtra("LOCATION", readableLocation)
                    startActivity(intent)
                }, 2000)
            }
        }
        setContentView(binding.root)
    }


}