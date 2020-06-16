package com.obregon.tekro.ui.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.obregon.tekro.R
import com.obregon.tekro.ui.model.User
import com.obregon.tekro.ui.utils.ItemTouchHelperAdapter
import java.util.*

class UserListAdapter(var cellLayoutFile:Int,
                      private val users:List<User>,
                      private val itemClickListener: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {
    //need to convert to ROOM + pagedList
    private var cachedUser=users.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(cellLayoutFile, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listHolder=(holder as ListViewHolder)
        val user=cachedUser[position]
        listHolder.tvUserName.text=user.name
        listHolder.ivUserImage.load(user.avatar)
        listHolder.itemView.setOnClickListener { itemClickListener(user) }
    }

    override fun getItemCount(): Int {
        return cachedUser.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivUserImage:ImageView = itemView.findViewById(R.id.iv_user_image)
        var tvUserName:TextView = itemView.findViewById(R.id.tv_user_name)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(this.cachedUser,fromPosition,toPosition)
        this.notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        cachedUser.removeAt(position)
        this.notifyItemRemoved(position)
    }

}





