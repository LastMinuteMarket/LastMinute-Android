package com.lastminute.ui.model

import java.time.LocalDateTime

data class ProductSummary(
    val id: Long,
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
        PREPAID(true), DEPOSIT(false)
    }
}