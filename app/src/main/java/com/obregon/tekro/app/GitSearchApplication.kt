package com.obregon.tekro.app

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GitSearchApplication: DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

   override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
      return DaggerAppComponent.factory().create(applicationContext)
    }
}