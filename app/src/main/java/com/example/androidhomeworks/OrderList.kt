package com.example.androidhomeworks

import java.util.UUID

object OrderList {
    val orderList = mutableListOf(
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1524",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 110,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1624",
            orderDate = 1620864000000L,
            trackingNum = "IK2873218897",
            quantity = 2,
            subTotal = 230,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1724",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 251,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1924",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 313,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1224",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 222,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1124",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 111,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1324",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 312,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #1921",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 512,
            status = "PENDING"
        ),
        OrderDetails(
            id = UUID.randomUUID(),
            orderName = "Order #2524",
            orderDate = 1620864000000L,
            trackingNum = "IK287368838",
            quantity = 2,
            subTotal = 111,
            status = "PENDING"
        ),
    )
}