package com.example.androidhomeworks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.data.presenter.PostPresenter
import com.example.androidhomeworks.databinding.PostBinding
import com.example.androidhomeworks.utils.loadImages

class PostsAdapter : ListAdapter<PostPresenter, PostsAdapter.PostsViewHolder>(PostDiffUtil()) {

    inner class PostsViewHolder(private val binding: PostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostPresenter) {
            binding.tvPostDesc.text = post.title
            binding.tvComments.text = post.comments
            binding.tvLikes.text = post.likes
            binding.tvPostFullName.text = post.ownerFullName
            binding.tvPostTime.text = post.formattedDate

            Glide.with(binding.ivPostImage.context)
                .load(post.ownerProfile)
                .placeholder(R.drawable.profile)
                .into(binding.ivPostImage)

            binding.loadImages(post.images)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = getItem(position)
        holder.onBind(post)
    }
}


class PostDiffUtil : DiffUtil.ItemCallback<PostPresenter>() {
    override fun areItemsTheSame(oldItem: PostPresenter, newItem: PostPresenter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostPresenter, newItem: PostPresenter): Boolean {
        return oldItem == newItem
    }

}