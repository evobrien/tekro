package com.obregon.tekro.app

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class GitSearchApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}