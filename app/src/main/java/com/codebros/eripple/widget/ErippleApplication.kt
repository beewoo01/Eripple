package com.codebros.eripple.widget

import android.app.Application
import android.content.Context
import com.codebros.eripple.R
//import com.kakao.sdk.common.KakaoSdk
import com.naver.maps.map.NaverMapSdk

class ErippleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("7uu9b4sb09")
        //KakaoSdk.init(this, getString(R.string.kakaoAppKey))
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}