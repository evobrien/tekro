package com.obregon.tekro.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obregon.tekro.R
import com.obregon.tekro.di.TekroViewModelFactory
import com.obregon.tekro.ui.detail.UserDetailFragment
import com.obregon.tekro.ui.model.User
import com.obregon.tekro.ui.utils.EndlessRecyclerViewScrollListener
import com.obregon.tekro.ui.utils.LoadMoreCallback
import com.obregon.tekro.ui.utils.SwipeAndDragHelper
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
import timber.log.Timber
import javax.inject.Inject

class UserListFragment : DaggerFragment(),LoadMoreCallback {

    @Inject lateinit var viewModelFactory: TekroViewModelFactory
    private val userListViewModel by viewModels<UserListViewModel> { viewModelFactory }

    private enum class LayoutManagerType {
        GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER
    }

    private var currentLayoutManagerType= LayoutManagerType.LINEAR_LAYOUT_MANAGER
    private val KEY_SEARCH_BAR_CONTENT:String="KEY_SEARCH_BAR_CONTENT"
    private val KEY_CUR_LIST_POS:String="KEY_CUR_LIST_POS"

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

    private lateinit var searchItem:MenuItem
    private lateinit var searchView: SearchView
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_list_menu, menu)
        searchItem= menu.findItem(R.id.app_bar_search)
        searchView= searchItem.actionView as SearchView
        setupSearchView(menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userListViewModel.users.observeForever {  layoutList(it)}
    }

    private fun print(users:List<User>){
        Timber.d("Users: $users")
    }

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: UserListAdapter


    private fun layoutList(users:List<User>){
        print(users)
        layoutManager = getLayoutManager()
        user_list.layoutManager=layoutManager
        adapter = UserListAdapter(getCellLayoutFile(),users, this::onClickRecyclerListItem)
        val swipeAndDragHelper = SwipeAndDragHelper(adapter)
        val touchHelper = ItemTouchHelper(swipeAndDragHelper)
        adapter.setTouchHelper(touchHelper)
        user_list.adapter = adapter
        touchHelper.attachToRecyclerView(this.user_list)
        val scrollListener=EndlessRecyclerViewScrollListener(layoutManager,this)
        user_list.addOnScrollListener(scrollListener)
        //user_list.setHasFixedSize(true);

    }

    private fun onClickRecyclerListItem(user:User){
        val args=Bundle()
        args.putParcelable("USER",user)
        val userDetailFragment=UserDetailFragment()
        userDetailFragment.arguments=args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.content,userDetailFragment,"UserDetailFragment")
            ?.addToBackStack("UserDetailFragment")
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
        searchView.queryHint = "Search by user name"
        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                lastQuery=query
                userListViewModel.getUsers(query)
                return true
            }

        })
    }

    private var lastQuery:String =""
    private var currentPos:Int=0
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_SEARCH_BAR_CONTENT,lastQuery)
        if(user_list.childCount>0){
            currentPos= (user_list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }
        outState.putInt(KEY_CUR_LIST_POS,currentPos)
        super.onSaveInstanceState(outState)
    }

    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
        Timber.d("Boundary ... Load more")
        userListViewModel.loadMore()
    }


}
