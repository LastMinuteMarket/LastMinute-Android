package com.lastminute.repository.model.product

import java.time.LocalDate

data class ProductCreateDto(
    val placement: PlacementDto,
    val detail: ProductDetailDto,
    val priceSchedules: List<PriceScheduleDto>
) {
    data class PriceScheduleDto(
        val applyAt: LocalDate,
        val price: Int
    )
}
