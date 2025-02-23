package com.example.androidhomeworks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.data.presenter.StoryPresenter
import com.example.androidhomeworks.databinding.StoryBinding

class StoryAdapter : ListAdapter<StoryPresenter, StoryAdapter.StoryViewHolder>(StoryDiffUtil()) {

    inner class StoryViewHolder(private val binding: StoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(story:StoryPresenter) {
            Glide.with(binding.ivStory.context)
                .load(story.cover)
                .placeholder(R.drawable.chair)
                .into(binding.ivStory)

            binding.tvTitle.text = story.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
       return StoryViewHolder(
           StoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
       )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.onBind(story)
    }
}

class StoryDiffUtil : DiffUtil.ItemCallback<StoryPresenter>() {
    override fun areItemsTheSame(oldItem: StoryPresenter, newItem: StoryPresenter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoryPresenter, newItem: StoryPresenter): Boolean {
        return oldItem == newItem
    }
}