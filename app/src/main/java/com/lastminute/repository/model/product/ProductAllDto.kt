package com.lastminute.repository.model.product

data class ProductAllDto(
    val productId: Long,
    val writer: UserProfileDto,
    val placement: PlacementDto,
    val detail: ProductDetailDto
)
