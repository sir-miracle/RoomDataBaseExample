package com.example.roomdatabaseexample.ui.recyclerviewadapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.User
import com.example.roomdatabaseexample.ui.fragments.list.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recyclerview_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        val id = holder.itemView.findViewById<TextView>(R.id.rc_tv_index)
        val firstName = holder.itemView.findViewById<TextView>(R.id.rc_tv_first_name)
        val lastName = holder.itemView.findViewById<TextView>(R.id.rc_tv_lastname)
        val age = holder.itemView.findViewById<TextView>(R.id.rc_tv_age)
        val rvRootHolder = holder.itemView.findViewById<View>(R.id.rowLayout)

        id.text = currentItem.id.toString()
        firstName.text = currentItem.firstName.toString()
        lastName.text = currentItem.lastName
        age.text = currentItem.age.toString()

        rvRootHolder.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }


}