package com.example.androidhomeworks

import java.util.UUID

data class OrderDetails(
    val id: UUID,
    val orderDate: Long,
    val orderName: String,
    val trackingNum: String,
    val quantity: Int,
    val subTotal: Int,
    var status: String
) {

}