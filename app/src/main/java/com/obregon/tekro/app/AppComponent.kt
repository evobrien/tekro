package com.obregon.tekro.app

import android.content.Context
import com.obregon.tekro.MainActivity
import com.obregon.tekro.ui.detail.UserDetailModule
import com.obregon.tekro.ui.list.UserListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class,NetworkModule::class,UserListModule::class,UserDetailModule::class])
interface AppComponent : AndroidInjector<GitSearchApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}
