package com.obregon.tekro.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module


@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsViewModelFactory(impl: TekroViewModelFactory): ViewModelProvider.Factory
}