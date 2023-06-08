package com.lastminute.repository.model

data class ProductSummaryDto(
    val productId: Long,
    val placement: PlacementDto,
    val detail: ProductDetailDto
)
