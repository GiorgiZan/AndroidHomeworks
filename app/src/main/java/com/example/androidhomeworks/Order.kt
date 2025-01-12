package com.example.androidhomeworks

import java.util.UUID

data class Order(
    val id: UUID,
    val orderImg: Int,
    val orderTitle: String,
    val orderColor: Int,
    val orderColorInText: String,
    val orderQuantity: Int,
    val orderStatus: StatusEnum,
    val orderPrice: Double,
    var orderAction: String,
    var orderReview: String? = null
) {
}