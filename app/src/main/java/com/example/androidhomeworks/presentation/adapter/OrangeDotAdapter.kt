package com.example.androidhomeworks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.OrangeDotBinding

class OrangeDotAdapter(private val dotCount: Int) :
    RecyclerView.Adapter<OrangeDotAdapter.RedDotViewHolder>() {

    inner class RedDotViewHolder(binding: OrangeDotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedDotViewHolder {
        val binding = OrangeDotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RedDotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedDotViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return dotCount
    }
}
