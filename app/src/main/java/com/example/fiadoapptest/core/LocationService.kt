package com.example.fiadoapptest.core

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.IBinder
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


private const val TAG = "LocationService"

class LocationService(private val owner: AppCompatActivity) : Service() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun requestLocationPermission(owner: AppCompatActivity) {
        val locationPermissionRequest = owner.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {}
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {}
                else -> {}

            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    fun isLocationEnabled(): Boolean {
        val locationManager = owner.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun getLastKnowLocation(): FusedLocationProviderClient {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(owner)
        if (ActivityCompat.checkSelfPermission(
                owner,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                owner,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission(owner)
        }
        return fusedLocationClient
    }

    fun getCityName(location: Location): String {
        val geocoder = Geocoder(owner)
        val address = geocoder.getFromLocation(location.latitude, location.longitude, 1).first()
        return "Felicidades estas en ${address.featureName},${address.adminArea},${address.countryName}"
    }
}