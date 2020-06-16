package com.obregon.tekro.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.api.load
import com.obregon.tekro.R
import com.obregon.tekro.di.TekroViewModelFactory
import com.obregon.tekro.ui.model.User
import com.obregon.tekro.ui.model.UserDetails
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_detail_fragment.*
import javax.inject.Inject

class UserDetailFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: TekroViewModelFactory
    private val viewModel by viewModels<UserDetailViewModel> { viewModelFactory }
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        user= this.arguments?.getParcelable<User>("USER") as User
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user?.name?.let { viewModel.getUser(it) }
        viewModel.users.observeForever { layout(it)}

    }

    private fun layout(user: UserDetails){
        user_avatar.load(user.avatar)
        user_name.text=user.displayName
        public_repos.text=user.numPublicRepos.toString()
        public_gists.text=user.numPublicGists.toString()
        num_followers.text=user.numFollowers.toString()
        num_following.text=user.numFollowing.toString()
    }

}