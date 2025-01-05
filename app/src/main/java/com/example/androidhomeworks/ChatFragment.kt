package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.databinding.FragmentChatBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val messageAdapter by lazy {
        MessageAdapter()
    }


    companion object {
        val messages: MutableList<Message> = mutableListOf()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()
    }


    private fun setUp() {
        val layoutManager = LinearLayoutManager(requireContext()).apply {
            reverseLayout = true  // reverse the order of items
            stackFromEnd = true   // start layout from the bottom
        }
        binding.RecyclerViewContainer.layoutManager = layoutManager
        binding.RecyclerViewContainer.adapter = messageAdapter
        messageAdapter.submitList(messages)
    }


    private fun listeners() {
        binding.ivSend.setOnClickListener {
            if (binding.etInputMessage.text.toString().isEmpty()) {
                binding.etInputMessage.error = getString(R.string.message_input_should_not_be_empty)
                return@setOnClickListener
            }

            val newMessage = Message(
                id = UUID.randomUUID(),
                text = binding.etInputMessage.text.toString(),
                date = formatDate()
            )

//            messages.add(newMessage) // if we want messages to be added to last position
            messages.add(0, newMessage)

            binding.RecyclerViewContainer.scrollToPosition(0) // we would write messages.size - 1 if we were adding messages normally

            binding.etInputMessage.text?.clear()
        }

        binding.ivBackIcon.setOnClickListener {
            requireActivity().finish()
        }


    }


    private fun formatDate(): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
        return "Today, ${formatter.format(currentTime)}"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}