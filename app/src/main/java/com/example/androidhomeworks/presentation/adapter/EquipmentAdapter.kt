package com.example.androidhomeworks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.EquipmentItemBinding
import com.example.androidhomeworks.presentation.model.GetEquipment

class EquipmentAdapter : ListAdapter<GetEquipment, EquipmentAdapter.EquipmentViewHolder>(EquipmentDiffUtil())  {


    inner class EquipmentViewHolder(private val binding: EquipmentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(equipment: GetEquipment) {
            binding.tvEquipName.text = equipment.name

            val childAdapter = OrangeDotAdapter(equipment.children.take(4))

            binding.recyclerViewOrangeDots.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = childAdapter
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        return EquipmentViewHolder(
            EquipmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val equipment = getItem(position)
        holder.onBind(equipment)
    }
}

class EquipmentDiffUtil : DiffUtil.ItemCallback<GetEquipment>() {
    override fun areItemsTheSame(oldItem: GetEquipment, newItem: GetEquipment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetEquipment, newItem: GetEquipment
    ): Boolean {
        return oldItem == newItem

    }
}