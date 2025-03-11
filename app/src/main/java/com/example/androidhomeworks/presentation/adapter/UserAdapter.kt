package com.example.androidhomeworks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.data.local.room.user.UserEntity
import com.example.androidhomeworks.databinding.UserRecyclerViewBinding

class UserDiffUtil : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}

class UserAdapter : PagingDataAdapter<UserEntity, UserAdapter.UserViewHolder>(UserDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.onBind(user)
        }
    }

    inner class UserViewHolder(private val binding: UserRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: UserEntity) {
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