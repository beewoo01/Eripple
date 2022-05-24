package com.codebros.eripple.screen.main.eripple_info

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentErippleInfoBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.*

class ErippleInfoFragment : BaseFragment<ErippleInfoViewModel, FragmentErippleInfoBinding>() {

    override val viewModel: ErippleInfoViewModel by viewModels()

    override fun getViewBinding(): FragmentErippleInfoBinding =
        FragmentErippleInfoBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private var mapView: MapView? = null

    private lateinit var locationManager: LocationManager
    private var preLoc: Location? = null
    private lateinit var bestProvider: String

    private lateinit var myLocationListener: LocationListener

    private var longitude: Double = 0.0
    private var latitude: Double = 0.0

    //private var locLnr : LocationListener = MyLocationListener()

    private val adapter by lazy {
        ModelRecyclerAdapter<Eripple, ErippleInfoViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,

            object : ErippleAdapterListener {
                override fun onItemClick(model: Eripple) {
                    Log.wtf(
                        "ErippleAdapterListener",
                        "onItemClick bookmark IDx ${model.bookmark_idx}"
                    )
                }

                override fun onHeartClick(model: Eripple) {

                    if (model.bookmark_idx > 0) {
                        viewModel.removeBookMark(model)
                    } else {
                        AccountInfoSingleton.account_idx?.let { viewModel.addBookMark(model, it) }
                    }

                }

                override fun onShearClick(model: Eripple) {
                    // TODO: 공유하기

                }
            }
        )
    }

    override fun initViews() {
        with(binding) {
            //getMyLocation()
            initLocationManager()
            erippleContainer.erippleRecyclerView.adapter = adapter
            erippleContainer.searchEdt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun afterTextChanged(p0: Editable?) {

                    viewModel.erippleList.value?.let {

                        if (it.count() > 0) {

                            search(erippleContainer.searchEdt.text.toString())
                        }

                    } ?: kotlin.run {

                    }

                }

            })
        }
    }

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                (it.key == Manifest.permission.ACCESS_FINE_LOCATION)
                        || (it.key == Manifest.permission.ACCESS_COARSE_LOCATION)
            }

            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                Log.wtf("locationPermissionLauncher", "true")
                initMapView()
                setMyLocationListener()
            } else {
                Log.wtf("locationPermissionLauncher", "false")

                Toast.makeText(
                    requireContext(),
                    getString(R.string.can_not_assigned_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime = 1500L
        val minDistance = 100f
        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }

        with(locationManager) {
            requestLocationUpdates(
                android.location.LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                myLocationListener
            )

            requestLocationUpdates(
                android.location.LocationManager.NETWORK_PROVIDER,
                minTime,
                minDistance,
                myLocationListener
            )
        }
    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager =
                requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        }

        val isGpsUnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsUnabled) {
            locationPermissionLauncher.launch(locationPermissions)
        }
    }

    override fun onResume() {
        super.onResume()
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
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            return
        }

        bestProvider = locationManager.getBestProvider(getCrireia(), true).toString()
        removeLocationListener()
        myLocationListener = MyLocationListener()
        locationManager.requestLocationUpdates(bestProvider, 3000L, 10.0f, myLocationListener)
        getMyLocation()

    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(myLocationListener)
        binding.mapContainer.removeAllViews()
    }

    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            //binding.locationTitleText.text = "${location.latitude}, ${location.longitude}"
            /*viewModel.loadReverseGeoInformation(
                LocationLatLngEntity(
                    location.latitude,
                    location.longitude
                )
            )*/
            //removeLocationListener()
            if (context != null) {
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
                        preLoc?.let { location ->
                            longitude = location.longitude
                            latitude = location.latitude
                        }

                    }
                }
            }

        }
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }


    private fun initLocationManager() {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager

    }

    private fun initMapView() {
        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), 1, true)
        Log.wtf("initMapView", "initMapView")
        mapView = MapView(requireActivity())
        mapView?.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                latitude,
                longitude
            ), true
        )
        mapView?.zoomIn(true)
        mapView?.zoomOut(true)
        binding.mapContainer.addView(mapView)
        resetMarkers()
    }

    private fun search(text: String) {
        Log.wtf("ErippleInfoFragment", "search ")
        Log.wtf("ErippleInfoFragment", "search $text")

        val list = mutableListOf<Model>()

        viewModel.erippleList.value?.let {
            if (text.isEmpty()) {

                list.addAll(it)

            } else {

                val currentList = mutableListOf<Model>()
                currentList.addAll(it)
                Log.wtf("ErippleInfoFragment", "list size ${currentList.size}")

                currentList.forEach { model ->
                    if (model is Eripple) {

                        val name =
                            model.eripple_name.replace(" ", "").lowercase(Locale.getDefault())
                                .contains(text.replace(" ", ""))

                        val address =
                            model.eripple_address.replace(" ", "").lowercase(Locale.getDefault())
                                .contains(text.replace(" ", ""))

                        if (name) {
                            list.add(model)
                        } else if (address) {
                            list.add(model)
                        }

                    }

                }
            }
        }

        adapter.submitList(list)
        //resetMarkers()

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

    private fun resetMarkers() {
        mapView?.let {
            it.removePOIItems(it.poiItems)
            viewModel.erippleList.value.let { list ->
                list?.let { erippleList ->
                    val markers = arrayOfNulls<MapPOIItem>(erippleList.size)
                    for ((index, model) in list.withIndex()) {
                        markers[index] = MapPOIItem()
                        markers[index]?.apply {
                            tag = model.eripple_idx
                            mapPoint = MapPoint.mapPointWithGeoCoord(
                                model.eripple_latitude.toDouble(),
                                model.eripple_longitude.toDouble()
                            )

                            itemName = model.eripple_name
                            markerType = MapPOIItem.MarkerType.RedPin
                        }
                    }
                    it.addPOIItems(markers)
                }
            }
        }
    }


    override fun observeData() {
        viewModel.erippleList.observe(this@ErippleInfoFragment) {
            adapter.currentList.toMutableList().clear()
            it?.let {
                adapter.submitList(it.toMutableList())
                resetMarkers()
            }

        }

        viewModel.erippleAddedBookmarkState.observe(this@ErippleInfoFragment) {

            it.second?.let { changeModel ->

                val list: MutableList<Model> = adapter.currentList
                val position = list.indexOf(it.first)

                if (position > -1) {
                    if (list[position] is Eripple) {
                        (list[position] as Eripple).bookmark_idx = changeModel.bookmark_idx
                    }
                    adapter.submitList(list)
                    adapter.notifyItemChanged(position)
                    //resetMarkers()
                }


                Toast.makeText(
                    requireContext(),
                    "${changeModel.eripple_name}를(을) 즐겨찾기에 등록하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()

            } ?: kotlin.run {

                Toast.makeText(requireContext(), "즐겨찾기 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show()

            }
        }

        viewModel.erippleRemovedState.observe(this@ErippleInfoFragment) {

            it?.let { result ->

                val list: MutableList<Model> = mutableListOf()
                list.addAll(adapter.currentList)
                val position = list.indexOf(result)

                if (position > -1) {
                    result.bookmark_idx = 0
                    list[position] = result
                    adapter.submitList(list)
                    adapter.notifyItemChanged(position)
                    //resetMarkers()
                    Toast.makeText(
                        requireContext(),
                        "${result.eripple_name}를(을) 즐겨찾기에서 삭제하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            } ?: kotlin.run {
                Toast.makeText(requireContext(), "즐겨찾기 삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.getErippleList(it) }
    }

    companion object {
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

}
