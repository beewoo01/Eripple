package com.codebros.eripple.widget.adapter.kakao

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codebros.eripple.R
import com.codebros.eripple.data.url.DefaultUrl
import com.codebros.eripple.extention.load
import com.codebros.eripple.model.eripple.Eripple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem

class CustomBalloonAdapter(inflater: LayoutInflater) : CalloutBalloonAdapter {

    @SuppressLint("InflateParams")
    private val balloonView: View = inflater.inflate(R.layout.location_marker_layout, null)
    private val eripple_name: TextView = balloonView.findViewById(R.id.eripple_name_txv)
    private val address: TextView = balloonView.findViewById(R.id.eripple_address_txv)
    private val eripple_img: ImageView = balloonView.findViewById(R.id.eripple_thumbnail_img)
    private val address_detail: TextView = balloonView.findViewById(R.id.address_detail_txv)

    @SuppressLint("SetTextI18n")
    override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
        poiItem?.let {

            if (it.userObject is Eripple) {
                val model = (it.userObject as Eripple)
                eripple_name.text = model.eripple_name
                address.text = model.eripple_address
                address_detail.text = model.eripple_address_detail

                CoroutineScope(Dispatchers.Main).launch {
                    eripple_img.load(DefaultUrl.SAMPLE_IMAGE_URL+ "eripple/" + model.eripple_thumbnail)
                }

            }

        }

        return balloonView
    }


    override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
        return balloonView
    }


}