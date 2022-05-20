package com.codebros.eripple.screen.main.eripple_info

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentErippleInfoBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.observeOnce
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener
import net.daum.mf.map.api.MapView
import java.util.*

class ErippleInfoFragment : BaseFragment<ErippleInfoViewModel, FragmentErippleInfoBinding>() {

    override val viewModel: ErippleInfoViewModel by viewModels()

    override fun getViewBinding(): FragmentErippleInfoBinding =
        FragmentErippleInfoBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val mapView by lazy {
        MapView(requireActivity())
    }

    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener: LocationListener

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
            getMyLocation()
             initMapView()
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

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                (it.key == Manifest.permission.ACCESS_FINE_LOCATION)
                        || (it.key == Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                setMyLocationListener()
            } else {
                /*with(binding.erippleContainer) {
                    setText(R.string.please_setUp_your_location_permission)
                    setOnClickListener {
                        getMyLocation()
                    }
                }*/
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
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        val isGpsUnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsUnabled) {
            locationPermissionLauncher.launch(locationPermissions)
        }
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
            removeLocationListener()
        }
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }
    
    private fun initMapView() {
        binding.mapContainer.addView(mapView)
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

    }

    override fun observeData() {
        viewModel.erippleList.observe(this@ErippleInfoFragment) {
            adapter.currentList.toMutableList().clear()
            adapter.submitList(it?.toMutableList())
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
