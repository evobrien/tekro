package com.obregon.tekro.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.obregon.tekro.R
import com.obregon.tekro.di.TekroViewModelFactory
import com.obregon.tekro.ui.model.User
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
import okhttp3.internal.notifyAll
import javax.inject.Inject

class UserListFragment : DaggerFragment(){

    @Inject lateinit var viewModelFactory: TekroViewModelFactory
    private val userListViewModel by viewModels<UserListViewModel> { viewModelFactory }

    private enum class LayoutManagerType {
        GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER
    }

    private var currentLayoutManagerType= LayoutManagerType.LINEAR_LAYOUT_MANAGER

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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userListViewModel.getUsers("Tom")
        userListViewModel.users.observeForever {  layoutList(it)}
    }

    private fun print(users:List<User>){
        Log.d("UserLog",users.toString())
    }

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UserListAdapter

    private fun layoutList(users:List<User>){
        print(users)
        layoutManager = getLayoutManager()
        user_list.layoutManager=layoutManager
        adapter = UserListAdapter(getCellLayoutFile(),users)
        user_list.adapter = adapter

    }

    private fun getCellLayoutFile():Int {
        return when (currentLayoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                R.layout.user_list_gid_cell
            }
            else -> {
                R.layout.user_list_cell
            }
        }
    }

    private fun getLayoutManager():LinearLayoutManager {
        return when (currentLayoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                GridLayoutManager(this.context, 3)
            }
            else -> {
                LinearLayoutManager(this.context)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_grid->{
                this.currentLayoutManagerType=LayoutManagerType.GRID_LAYOUT_MANAGER
            }
            else->{
                this.currentLayoutManagerType=LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }
        userListViewModel.users.value?.let { layoutList(it) }
        return true
    }
}