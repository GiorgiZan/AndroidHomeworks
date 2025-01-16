package com.example.androidhomeworks.box

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.BoxRecyclerViewBinding
import com.example.androidhomeworks.field.FieldAdapter

class BoxAdapter(private val boxes: List<FieldBox>) :
    RecyclerView.Adapter<BoxAdapter.BoxViewHolder>() {

    inner class BoxViewHolder(private val binding: BoxRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val box = boxes[adapterPosition]
            val innerAdapter = FieldAdapter()

            innerAdapter.submitList(box.fields)

            binding.boxRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = innerAdapter
                setRecycledViewPool(RecyclerView.RecycledViewPool())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        return BoxViewHolder(
            BoxRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return boxes.size
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        holder.onBind()
    }
}