package com.codebros.eripple.widget

import android.app.Application
import android.content.Context
import com.codebros.eripple.R
import com.kakao.sdk.common.KakaoSdk

class ErippleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        KakaoSdk.init(this, getString(R.string.kakaoAppKey))
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