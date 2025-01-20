package com.example.androidhomeworks

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.databinding.FragmentChatBinding


class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {
    private val chatAdapter by lazy {
        ChatAdapter()
    }
    private val chatViewModel:MyViewModel by viewModels()

    override fun setUp() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = chatAdapter
        chatViewModel.getChatList()?.let { chatList ->
            chatAdapter.submitList(chatList)
        }
    }

    override fun listeners() {
        binding.btnFilter.setOnClickListener {
            val nameToFilter = binding.tvFilterField.text.toString()
            val filteredList = chatViewModel.filterChatsByName(nameToFilter)
            chatAdapter.submitList(filteredList)
        }
    }

}