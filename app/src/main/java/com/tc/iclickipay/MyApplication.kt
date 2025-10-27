package com.tc.iclickipay

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // You can initialize app-wide resources here if needed

    }
}
