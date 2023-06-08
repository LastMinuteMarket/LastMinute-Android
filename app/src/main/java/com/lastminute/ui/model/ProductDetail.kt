package com.lastminute.ui.model

import java.time.LocalDateTime

data class ProductDetail(
    val productId: Int,
    val title: String,
    val menu: String,
    val numPeoples: Int,
    val address: String,
    val lat: Double,
    val lot: Double,
    val writerId: Int,
    val writer: String,
    val time: LocalDateTime,
    val isAllPaid: Boolean,
    val paid: Int,
    val price: Int,
    val content: String
)
