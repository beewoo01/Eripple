package com.codebros.eripple.screen.main

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.codebros.eripple.databinding.FragmentTestBinding
import com.codebros.eripple.screen.base.BaseFragment
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class TestFragment : BaseFragment<TestViewModel, FragmentTestBinding>() {

    private var provider: String? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var preLoc: Location? = null
    private lateinit var locationManager: LocationManager
    private lateinit var bestProvider: String
    private lateinit var mapView: MapView
    private var locLnr: LocationListener = DispLocListener()

    override val viewModel: TestViewModel by viewModels()

    override fun getViewBinding(): FragmentTestBinding = FragmentTestBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()


        //initViews()
    }

    override fun initViews() {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            return
        }
        bestProvider = locationManager.getBestProvider(getCrireia(), true).toString()
        locLnr = DispLocListener()
        locationManager.requestLocationUpdates(bestProvider, 3000L, 10.0f, locLnr)
        getPermission()
    }

    private fun getCrireia(): Criteria {
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isCostAllowed = false
        return criteria
    }


    private fun getPermission() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            Log.wtf("getPermission", "true")
        } else {
            Log.wtf("getPermission", "else")
            locationManager.getLastKnownLocation(bestProvider)?.let {
                preLoc = it
            }

            preLoc?.let {
                Log.wtf("getLocation", "location notnull")
                provider = it.provider
                longitude = it.longitude
                latitude = it.latitude

                setMapView()
                //getData()
            } ?: run {
                Log.wtf("getLocation", "location null")
            }

            //marker = MapPOIItem()
            Log.wtf("latitude", latitude.toString())
            Log.wtf("longitude", longitude.toString())

        }


    }

    private fun setMapView() {
        mapView = MapView(requireActivity())

        Log.wtf("setMapView", "true")
        mapView.setMapCenterPointAndZoomLevel(
            MapPoint.mapPointWithGeoCoord(latitude, longitude),
            1,
            true
        )
        mapView.zoomIn(true)
        mapView.zoomOut(true)
        val marker = MapPOIItem()

        val mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.mapPoint = mapPoint
        marker.itemName = "현재위치"
        marker.tag = 0
        marker.markerType = MapPOIItem.MarkerType.BluePin
        mapView.addPOIItem(marker)
        binding.mapLayout.addView(mapView)
    }


    inner class DispLocListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    0
                )

            } else {
                locationManager.getLastKnownLocation(bestProvider)?.let {
                    preLoc = it
                }
            }


        }
    }


}