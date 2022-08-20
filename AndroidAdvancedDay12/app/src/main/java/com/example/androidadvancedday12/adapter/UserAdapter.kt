package com.example.androidadvancedday12.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvancedday12.databinding.ItemUserBinding
import com.example.androidadvancedday12.model.User

class UserAdapter(var userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var listUserFiltered: ArrayList<User> = ArrayList()

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(user: User) {
            binding.tvId.text = user.id.toString()
            binding.tvName.text = user.username
            binding.tvGender.text = user.gender
            binding.tvClass.text = user.classname

//            binding.button.setOnClickListener {
//                onClick?.invoke(user)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = userList[position]
        holder.binItem(user)
    }

    override fun getItemCount(): Int = userList.size


}