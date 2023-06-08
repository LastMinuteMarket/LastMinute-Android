package com.lastminute.repository.model.placement

data class NaverDto<T>(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<T>
)
