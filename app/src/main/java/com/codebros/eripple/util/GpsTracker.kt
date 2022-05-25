package com.codebros.eripple.util

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import androidx.core.content.ContextCompat
import kotlin.math.log

class GpsTracker(private val context: Context, private val locationManager: LocationManager?) :
     LocationListener {

    private var location: Location? = null
    private var latitude: Double? = null
    private var longitude: Double? = null
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES = 10F
    private val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong()


    init {
        getLocation()
    }

    private fun getLocation(): Location? {
        locationManager?.let {
            val isGPSEnabeld = it.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = it.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            val hasFineLocationPermission =
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            val hasCoarseLocationPermission =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
            ) {

                if (isNetworkEnabled || isGPSEnabeld) {

                    if (location == null) {
                        location = it.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                        it.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            this
                        )


                        location?.let { loc ->
                            latitude = loc.latitude
                            longitude = loc.longitude

                        }

                    }

                }

            }
        }

        return location

    }

    fun getLatitude(): Double? {
        if (location != null) {
            latitude = location?.latitude
        }

        return latitude
    }

    fun getLongitude(): Double? {
        if (location != null) {
            longitude = location?.longitude
        }

        return longitude
    }



    override fun onLocationChanged(p0: Location) {

    }

}