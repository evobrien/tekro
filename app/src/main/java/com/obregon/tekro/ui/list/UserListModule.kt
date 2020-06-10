package com.obregon.tekro.ui.list

import androidx.lifecycle.ViewModel
import com.obregon.tekro.di.ViewModelFactoryModule
import com.obregon.tekro.di.ViewModelKey
import com.obregon.tekro.ui.detail.UserDetailFragment
import com.obregon.tekro.ui.detail.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserListModule {

    @ContributesAndroidInjector(modules = [
        ViewModelFactoryModule::class
    ])
    internal abstract fun userListFragment(): UserListFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindViewModel(viewmodel: UserListViewModel): ViewModel
}