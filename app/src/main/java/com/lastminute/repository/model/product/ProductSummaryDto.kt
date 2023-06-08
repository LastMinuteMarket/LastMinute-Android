package com.lastminute.repository.model.product

data class ProductSummaryDto(
    val productId: Long,
    val placement: PlacementDto,
    val detail: ProductDetailDto
)
