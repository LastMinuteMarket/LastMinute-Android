package com.lastminute.repository.model

data class CommonResponse<T>(
    val success: Boolean?,
    val data: T?,
    val message: String?,
    val errorCode: String?
)
