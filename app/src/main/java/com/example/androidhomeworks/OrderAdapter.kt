package com.example.androidhomeworks

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.OrderRecyclerViewBinding

class OrderDiffUtil : DiffUtil.ItemCallback<OrderDetails>() {
    override fun areItemsTheSame(oldItem: OrderDetails, newItem: OrderDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrderDetails, newItem: OrderDetails): Boolean {
        return oldItem == newItem
    }

}


class OrderAdapter(
    private val onDetailsButtonClick: (Bundle) -> Unit
) : ListAdapter<OrderDetails, OrderAdapter.OrderViewHolder>(OrderDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            OrderRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val filteredList = getItem(position)
        filterByStatus(filteredList.status)
        holder.onBind(filteredList)

        val bundle = Bundle().apply {
            putSerializable("Id", filteredList.id)
        }


        holder.binding.btnOrderDetails.setOnClickListener {
            onDetailsButtonClick(bundle)
        }

    }

    fun filterByStatus(status: String) {
        val filteredList = OrderList.orderList.map { it.copy() }
            .filter { it.status.equals(status, ignoreCase = true) }
        submitList(filteredList)
    }

    inner class OrderViewHolder(val binding: OrderRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(orderDetails: OrderDetails) {
            binding.tvOrderName.text = orderDetails.orderName
            binding.tvOrderDate.text =
                HelperFunctions.convertMillisecondsToDate(orderDetails.orderDate)
            binding.tvDisplayOrderTrackingNumber.text = orderDetails.trackingNum
            binding.tvDisplayOrderSubtotal.text = "$${orderDetails.subTotal}"
            binding.tvDisplayOrderQuantity.text = orderDetails.quantity.toString()
            binding.tvOrderStatus.text = orderDetails.status
            statusColor(orderDetails.status, binding)
        }
    }

    private fun statusColor(status: String, binding: OrderRecyclerViewBinding) {
        when (status) {
            "PENDING" -> binding.tvOrderStatus.setTextColor(Color.parseColor("#CF6212"))
            "DELIVERED" -> binding.tvOrderStatus.setTextColor(Color.parseColor("#009254"))
            "CANCELLED" -> binding.tvOrderStatus.setTextColor(Color.parseColor("#C50000"))
            else -> binding.tvOrderStatus.setTextColor(Color.BLACK)
        }
    }

}