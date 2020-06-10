package com.obregon.tekro.ui.detail

import androidx.fragment.app.viewModels
import com.obregon.tekro.di.TekroViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserDetailFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: TekroViewModelFactory
    private val viewModel by viewModels<UserDetailViewModel> { viewModelFactory }


}