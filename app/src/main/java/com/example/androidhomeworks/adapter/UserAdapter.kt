package com.example.androidhomeworks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.database.UserEntity

import com.example.androidhomeworks.databinding.UserItemBinding

class UserAdapter: ListAdapter<UserEntity, UserAdapter.UserViewHolder>(UserDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(user: UserEntity){
            binding.tvFirstName.text = user.first_name
            binding.tvLastName.text = user.last_name
            binding.tvAbout.text = user.about


            binding.ivUserIcon.apply {
                Glide.with(context)
                    .load(user.avatar)
                    .placeholder(R.drawable.defualt_icon)
                    .into(this)


            }

            binding.tvStatus.text = getActivationStatusText(user.activation_status)
        }
    }

    private fun getActivationStatusText(status: Double): String {
        return when (status) {
            in Int.MIN_VALUE.toDouble().. 0.0 -> "User not activated"
            1.0 -> "Online"
            in 2.0..22.0 -> "Online a few minutes ago"
            in 23.0..Int.MAX_VALUE.toDouble() -> "Online a few hours ago"
            else -> "Inactive for so long that user might be dead"
        }
    }

}

class UserDiffUtil : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}