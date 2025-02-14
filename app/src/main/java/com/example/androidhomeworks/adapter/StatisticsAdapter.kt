package com.example.androidhomeworks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.R
import com.example.androidhomeworks.data.remote.statistics.StatisticsDto
import com.example.androidhomeworks.databinding.StaticticsItemBinding


class StatisticsAdapter :
    ListAdapter<StatisticsDto, StatisticsAdapter.StatisticsViewHolder>(StatisticsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        return StatisticsViewHolder(
            StaticticsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val statistics = getItem(position)
        holder.bind(statistics)
    }

    class StatisticsViewHolder(private val binding: StaticticsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(statistics: StatisticsDto) {
            binding.tvMainDesc.text = statistics.title
            binding.tvPrice.text = statistics.price
            binding.tvLocation.text = statistics.location
            binding.tvReactionCount.text = statistics.reactionCount.toString()


            Glide.with(binding.image.context)
                .load(statistics.cover)
                .placeholder(R.drawable.chair)
                .into(binding.image)

            statistics.rate?.let {
                binding.ratingBar.rating = it.toFloat()
            } ?: run {
                binding.ratingBar.visibility = View.GONE
            }
        }
    }


}

class StatisticsDiffUtil : DiffUtil.ItemCallback<StatisticsDto>() {
    override fun areItemsTheSame(oldItem: StatisticsDto, newItem: StatisticsDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StatisticsDto, newItem: StatisticsDto): Boolean {
        return oldItem == newItem
    }
}