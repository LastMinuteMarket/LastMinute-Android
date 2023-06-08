package com.lastminute.repository.model.product

import java.time.LocalDateTime

data class ProductDetailDto(
    val menu: String,
    val description: String,
    val reservedPeoples: Int,
    val reservedTime: LocalDateTime,
    val reservationType: String,
    val pricePaid: Int,
    val priceNow: Int
)
