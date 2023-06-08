package com.lastminute.repository.model.auth

data class SignupDto(
    val nickname: String,
    val email: String,
    val password: String
)