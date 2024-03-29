package com.codebros.eripple.screen.main.eripple_info

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.health.connect.datatypes.DistanceRecord
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
import androidx.navigation.fragment.navArgs
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentErippleInfoBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.util.FragmentCommunication
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.GpsTracker
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.kakao.CustomBalloonAdapter
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Align
import com.naver.maps.map.overlay.Marker
//import net.daum.mf.map.api.MapPOIItem
//import net.daum.mf.map.api.MapPoint
//import net.daum.mf.map.api.MapView
import java.util.*

class ErippleInfoFragment(
    val listener: FragmentCommunication.ErippleInfo = object : FragmentCommunication.ErippleInfo {
        override fun onClick(idx: Int) {
            Log.wtf("ErippleInfoFragment", "onClick")
        }

    }
) : BaseFragment<ErippleInfoViewModel, FragmentErippleInfoBinding>(),
    OnMapReadyCallback {

    override val viewModel: ErippleInfoViewModel by viewModels()

    //private val args: ErippleInfoFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentErippleInfoBinding =
        FragmentErippleInfoBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }


    private lateinit var naverMap: NaverMap

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
            Log.wtf("Eripple", "initViews")
//            mapView = binding.mapView
            //mapView = requireActivity().supportFragmentManager.findFragmentById(R.id.mapContainer) as MapFragment

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

            if (responsePermissions.filter { it.value }.size == locationPermissions.size) {
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

    //카카오맵
    /*@SuppressLint("MissingPermission")
    private fun setMyLocationListener() {

        val gpsTracker = GpsTracker(requireContext(), locationManager)

        gpsTracker.getLatitude()?.let {
            latitude = it
        }

        gpsTracker.getLongitude()?.let {
            longitude = it
        }

        if (latitude == 0.0 || longitude == 0.0) {
            mapView?.setZoomLevel(12, true)
        } else {
            mapView?.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true)
        }

    }*/

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {

        GpsTracker(requireContext(), locationManager).apply {
            getLatitude()?.let { latitude = it }
            getLongitude()?.let { longitude = it }
        }


        if (::naverMap.isInitialized) {
            naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
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

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        Log.wtf("ErippleFragment", "onResume")
        binding.mapView.onResume()
        getMyLocation()
        binding.mapView.getMapAsync(this)

    }

    override fun onPause() {
        super.onPause()
        //locationManager.removeUpdates(myLocationListener)
        binding.mapView.onPause()
        //binding.mapContainer.removeAllViews()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }


    private fun initLocationManager() {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private fun initMapView() {
        if (binding.mapView == null) {
            Log.wtf("initMapView", "mapview is null!!")
            /*mapView = MapView(requireActivity())

            mapView?.zoomIn(true)
            mapView?.zoomOut(true)
            binding.mapContainer.addView(mapView)*/
            resetMarkers()
        } else {
            Log.wtf("initMapView", "mapview is not null")
        }

    }

    private fun search(text: String) {
        val list = mutableListOf<Model>()

        viewModel.erippleList.value?.let {
            if (text.isEmpty()) {

                list.addAll(it)

            } else {

                val currentList = mutableListOf<Model>()
                currentList.addAll(it)

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

    }

    /*private fun getCrireia(): Criteria {
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isCostAllowed = false
        return criteria
    }*/


    private fun resetMarkers() {

        viewModel.erippleList.value.let { list ->
            list?.let { erippleList ->
                //val markers = arrayOfNulls<MapPOIItem>(erippleList.size)
                val markers = arrayOfNulls<Marker>(erippleList.size)
                for ((index, model) in list.withIndex()) {
                    val marker = Marker().apply {
                        position = LatLng(
                            model.eripple_latitude.toDouble(),
                            model.eripple_longitude.toDouble()
                        )

                        captionText = model.eripple_name
                        setCaptionAligns(Align.Top)
                    }

                    markers[index] = marker
                    markers[index]?.map = naverMap
                }

                val arg: Int = arguments?.getInt("selected_idx") ?: 0

                if (arg > 0) {
                    erippleList.filter { filterEripple ->
                        filterEripple.eripple_idx == arg
                    }.run {
                        longitude = last().eripple_longitude.toDouble()
                        latitude = last().eripple_latitude.toDouble()
                        Log.wtf("last longi", last().eripple_longitude)
                        Log.wtf("last latit", last().eripple_latitude)
                        naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
                    }
                }

            }
        }

        //val selectedErippleIdx = args.selectedIdx
        /*mapView?.let {
            it.removePOIItems(it.poiItems)
            it.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))
            viewModel.erippleList.value.let { list ->
                list?.let { erippleList ->
                    val markers = arrayOfNulls<MapPOIItem>(erippleList.size)
                    for ((index, model) in list.withIndex()) {
                        markers[index] = MapPOIItem()
                        markers[index]?.apply {
                            userObject = model
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
                    if (selectedErippleIdx > 0) {
                        erippleList.filter { filterEripple ->
                            filterEripple.eripple_idx == selectedErippleIdx
                        }.run {
                            longitude = last().eripple_longitude.toDouble()
                            latitude = last().eripple_latitude.toDouble()
                            Log.wtf("last longi", last().eripple_longitude)
                            Log.wtf("last latit", last().eripple_latitude)
                            mapView?.setMapCenterPoint(
                                MapPoint.mapPointWithGeoCoord(
                                    latitude,
                                    longitude
                                ), true
                            )
                        }
                    }
                }
            }
        }*/
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

    override fun onMapReady(p0: NaverMap) {
        Log.wtf("ErippleFragment", "onMapReady")
        this.naverMap = p0
        naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
        getMyLocation()

    }



}
