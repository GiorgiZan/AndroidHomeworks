package com.example.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.OrderStatusRecyclerViewBinding

class OrderStatusDiff : DiffUtil.ItemCallback<OrderStatus>() {
    override fun areItemsTheSame(oldItem: OrderStatus, newItem: OrderStatus): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrderStatus, newItem: OrderStatus): Boolean {
        return oldItem == newItem
    }
}


class OrderStatusAdapter(
    private val onStatusSelected: (String) -> Unit
) :
    ListAdapter<OrderStatus, OrderStatusAdapter.OrderStatusViewHolder>(OrderStatusDiff()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        return OrderStatusViewHolder(
            OrderStatusRecyclerViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderStatusViewHolder, position: Int) {
        val orderStatus = getItem(position)
        holder.onBind(orderStatus)
    }

    inner class OrderStatusViewHolder(private val binding: OrderStatusRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(orderStatus: OrderStatus) {
            binding.btnStatus.text = orderStatus.status

            if (orderStatus.activated) {
                binding.btnStatus.setTextColor(binding.root.context.getColor(R.color.white))
                binding.btnStatus.setBackgroundResource(R.drawable.oval_active)
            } else {
                binding.btnStatus.setTextColor(binding.root.context.getColor(R.color.black))
                binding.btnStatus.setBackgroundResource(R.drawable.oval_inactive)
            }


            binding.btnStatus.setOnClickListener {
                OrderStatusList.statusList.forEachIndexed { index, item ->
                    item.activated = (index == adapterPosition)}

                submitList(OrderStatusList.statusList.toList())

                val selectedStatus = OrderStatusList.statusList[adapterPosition].status
                onStatusSelected(selectedStatus)
            }

        }
    }

}