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

class UserListAdapter(var cellLayoutFile:Int, private val users:List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(cellLayoutFile, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listHolder=(holder as ListViewHolder)
        if(this.cellLayoutFile== R.layout.user_list_gid_cell){
            listHolder.tvUserName.visibility= View.GONE
        }else{
            listHolder.tvUserName.text=users.get(position).name
            listHolder.tvUserName.visibility= View.VISIBLE
        }
        listHolder.ivUserImage.load(users.get(position).avatar)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivUserImage:ImageView
        var tvUserName:TextView

        init{
            ivUserImage=itemView.findViewById(R.id.iv_user_image)
            tvUserName =itemView.findViewById(R.id.tv_user_name)
        }

    }

}





