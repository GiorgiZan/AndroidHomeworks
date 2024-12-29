package com.example.androidhomeworks

object OrderStatusList {
    var statusList = mutableListOf(
        OrderStatus(
            id = 1,
            status = "Pending",
            activated = true
        ),
        OrderStatus(
            id = 2,
            status = "Delivered",
            activated = false
        ),
        OrderStatus(
            id = 3,
            status = "Cancelled",
            activated = false
        ),
    )
}