package com.obregon.tekro.ui.detail

import androidx.lifecycle.ViewModel
import com.obregon.tekro.di.ViewModelFactoryModule
import com.obregon.tekro.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserDetailModule {

    @ContributesAndroidInjector(modules = [
        ViewModelFactoryModule::class
    ])
    internal abstract fun userDetailFragment(): UserDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindViewModel(viewmodel: UserDetailViewModel): ViewModel
}