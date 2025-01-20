package com.example.androidhomeworks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomeworks.databinding.ChatRecyclerViewBinding

class ChatDiffUtil:DiffUtil.ItemCallback<ChatDto>() {
    override fun areItemsTheSame(oldItem: ChatDto, newItem: ChatDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatDto, newItem: ChatDto): Boolean {
        return oldItem == newItem
    }
}

class ChatAdapter : ListAdapter<ChatDto, ChatAdapter.ChatViewHolder>(ChatDiffUtil()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ChatRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind()
    }


    inner class ChatViewHolder(private val binding: ChatRecyclerViewBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(){
            val chat = getItem(adapterPosition)
            with(binding){
                ivPersonIcon.apply {
                    Glide.with(context)
                        .load(chat.image.takeIf { !it.isNullOrEmpty() })
                        .into(this)

                    tvPersonName.text = chat.owner
                    if (chat.lastMessageType == "text"){
                        tvActionIcon.visibility = View.GONE
                    }
                    else if (chat.lastMessageType == "file"){
                        tvActionIcon.setBackgroundResource(R.drawable.attach)
                    }
                    else if (chat.lastMessageType == "voice"){
                        tvActionIcon.setBackgroundResource(R.drawable.audio_icon)
                    }
                    tvPersonActionText.text = chat.lastMessage
                    tvPersonTime.text = chat.lastActive
                    if (chat.isTyping== true){
                        tvTyping.visibility = View.VISIBLE
                    }
                    if (chat.unreadMessages > 0){
                        tvNumberOfMessages.visibility = View.VISIBLE
                        tvPersonActionText.setTextColor(Color.WHITE)
                        tvNumberOfMessages.text = chat.unreadMessages.toString()
                    }
                }
            }

        }
    }
}