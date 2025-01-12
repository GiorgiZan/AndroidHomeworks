package com.example.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.OrderRecyclerViewBinding
import java.util.Locale
import java.util.UUID


class OrderDiffUtils : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }

}

class OrderAdapter(val reviewClick: (UUID) -> Unit) :
    ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            OrderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.onBind()
    }


    inner class OrderViewHolder(val binding: OrderRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val order = getItem(adapterPosition)
            with(binding) {
                ivOrderImg.setImageResource(order.orderImg)
                tvOrderTitle.text = order.orderTitle
                tvOrderColor.text = order.orderColorInText
                colorCircle.setBackgroundResource(order.orderColor)
                tvOrderQuantity.text = "Qty = ${order.orderQuantity}"
                tvOrderStatus.text = order.orderStatus.value
                tvOrderPrice.text = "$${String.format(Locale.CHINA, "%.2f", order.orderPrice)}"
                tvActionButton.text = order.orderAction

                tvActionButton.setOnClickListener {
                    if (order.orderAction == "Leave a review") {
                        reviewClick.invoke(order.id)
                    }
                }
            }
        }
    }
}