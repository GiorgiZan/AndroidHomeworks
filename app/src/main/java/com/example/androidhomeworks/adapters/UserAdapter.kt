package com.example.androidhomeworks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.UserRecyclerViewBinding
import com.example.androidhomeworks.dto_response.UsersDto

class UserDiffUtil : DiffUtil.ItemCallback<UsersDto.User>() {
    override fun areItemsTheSame(oldItem: UsersDto.User, newItem: UsersDto.User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UsersDto.User, newItem: UsersDto.User): Boolean {
        return oldItem == newItem
    }
}

class UserAdapter : ListAdapter<UsersDto.User, UserAdapter.UserViewHolder>(UserDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind()
    }

    inner class UserViewHolder(private val binding: UserRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {

            val user = getItem(adapterPosition)
            with(binding) {
                ivUserPicture.apply {
                    Glide.with(context)
                        .load(user.avatar.takeIf { it.isNotEmpty() })
                        .placeholder(R.drawable.default_icon)
                        .into(this)
                }
                tvFullName.text = user.firstName.plus(" ").plus(user.lastName)
                tvEmail.text = user.email
            }
        }
    }
}