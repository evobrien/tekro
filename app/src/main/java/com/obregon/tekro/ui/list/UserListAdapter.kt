package com.obregon.tekro.ui.list


import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.obregon.tekro.R
import com.obregon.tekro.ui.model.User
import com.obregon.tekro.ui.utils.SwipeAndDragHelper
import java.util.*

class UserListAdapter(var cellLayoutFile:Int,
                      users:List<User>,
                      private val itemClickListener: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    SwipeAndDragHelper.ActionCompletionContract {
    //need to convert to ROOM + pagedList
    private var cachedUser=users.toMutableList()
    private var touchHelper: ItemTouchHelper?=null
    private lateinit var recyclerView:RecyclerView

    fun setTouchHelper( touchHelper: ItemTouchHelper){
        this.touchHelper=touchHelper
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(cellLayoutFile, parent, false)
        recyclerView=parent as RecyclerView
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        val listHolder=(holder as ListViewHolder)
        val user=cachedUser[position]
        with(listHolder) {
            tvUserName.text=user.name
            ivUserImage.load(user.avatar)
            itemView.setOnClickListener { itemClickListener(user) }
            if(recyclerView.layoutManager !is  GridLayoutManager){
                handle?.setOnTouchListener { _, event ->
                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        touchHelper?.startDrag(this)
                    }
                    false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cachedUser.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivUserImage:ImageView = itemView.findViewById(R.id.iv_user_image)
        var tvUserName:TextView = itemView.findViewById(R.id.tv_user_name)
        var handle:ImageView?=itemView.findViewById(R.id.handle)

        fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onViewMoved(oldPosition: Int, newPosition: Int) {
        Collections.swap(this.cachedUser,oldPosition,newPosition)
        this.notifyItemMoved(oldPosition,newPosition)
    }

    override fun onViewSwiped(position: Int) {
        cachedUser.removeAt(position)
        this.notifyItemRemoved(position)
    }

}






