package com.codebros.eripple.widget

import android.app.Application
import android.content.Context

class ErippleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
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