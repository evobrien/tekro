package com.obregon.tekro.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.obregon.tekro.R
import com.obregon.tekro.data.response.UserSummary
import com.obregon.tekro.di.TekroViewModelFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class UserListFragment : DaggerFragment(){

    //private lateinit var toolbar: Toolbar;
    @Inject lateinit var viewModelFactory: TekroViewModelFactory;
    private val userListViewModel by viewModels<UserListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
        /*val rootView: View = inflater.inflate(R.layout.user_list_fragment, container, false)
        toolbar = rootView.findViewById(R.id.toolbar_user_list) as Toolbar
        val activityCompat=this.activity as AppCompatActivity
        activityCompat.setSupportActionBar(toolbar)
        return rootView*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toolbar.inflateMenu(R.menu.user_list_menu)
       // val menu = toolbar.menu
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userListViewModel.getUsers("Tom")
        userListViewModel.users.observe(viewLifecycleOwner, Observer { print(it)})
    }

    private fun print(summary:List<UserSummary>){
        Log.d("Summary",summary.toString())
    }
}