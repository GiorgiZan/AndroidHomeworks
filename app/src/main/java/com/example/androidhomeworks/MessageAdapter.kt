package com.example.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.MessageLeftRecyclerViewBinding
import com.example.androidhomeworks.databinding.MessageRightRecyclerViewBinding

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffUtil()) {

    companion object {
        const val LEFT_MESSAGE = 1
        const val RIGHT_MESSAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LEFT_MESSAGE) {
            return LeftMessageHolder(
                MessageLeftRecyclerViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else
            return RightMessageHolder(
                MessageRightRecyclerViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftMessageHolder) {
            holder.onBind()
        } else if (holder is RightMessageHolder) {
            holder.onBind()
        }
    }

    inner class LeftMessageHolder(private val binding: MessageLeftRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val item = getItem(adapterPosition)
            binding.tvMessageBox.text = item.text
            binding.tvDate.text = item.date

        }
    }

    inner class RightMessageHolder(private val binding: MessageRightRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val item = getItem(adapterPosition)
            binding.tvMessageBox.text = item.text
            binding.tvDate.text = item.date
        }
    }

    override fun getItemViewType(position: Int): Int {
        val reversedPosition = currentList.size - position - 1
        return if (reversedPosition % 2 == 0) LEFT_MESSAGE else RIGHT_MESSAGE // again if we wanted to add messages normally we would write position instead of reversedPosition
    }


}

class MessageDiffUtil : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}