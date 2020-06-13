package com.obregon.tekro.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.obregon.tekro.R
import com.obregon.tekro.di.TekroViewModelFactory
import com.obregon.tekro.ui.detail.UserDetailFragment
import com.obregon.tekro.ui.model.User
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
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
        setupSearchView(menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        adapter = UserListAdapter(getCellLayoutFile(),users, this::onClickItem)
        user_list.adapter = adapter
    }

    private fun onClickItem(user:User){
        Log.d("USER",user.toString())

        val args=Bundle()
        args.putParcelable("USER",user)
        val userDetailFragment=UserDetailFragment()
        userDetailFragment.arguments=args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.root,userDetailFragment,"UserDetailsFragment")
            ?.addToBackStack("UserDetailsFragment")
            ?.commit()
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
            R.id.action_list->{
                this.currentLayoutManagerType=LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }
        userListViewModel.users.value?.let { layoutList(it) }
        return true
    }

    private fun setupSearchView(menu:Menu){
        val searchItem= menu.findItem(R.id.app_bar_search)
        val searchView:SearchView= searchItem.actionView as SearchView
        searchView.queryHint = "Search by user name"
        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                userListViewModel.getUsers(query)
                return true
            }

        })

    }

}