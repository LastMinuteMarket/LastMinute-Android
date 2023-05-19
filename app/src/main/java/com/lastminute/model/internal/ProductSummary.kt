package com.lastminute.model.internal

import java.time.LocalDateTime

data class ProductSummary(
    val id: Int,
    val lat: Double,
    val lot: Double,
    val title: String,
    val menu: String,
    val time: LocalDateTime,
    val paid: String,
    val price: String,
    val likes: String,
    val type: ProductType
) {
    enum class ProductType(val activate: Boolean) {
        ALL_PAID(true), BOOKED(false)
    }
}