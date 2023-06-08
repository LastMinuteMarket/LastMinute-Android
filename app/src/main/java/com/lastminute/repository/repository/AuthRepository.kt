package com.lastminute.repository.repository

import com.lastminute.repository.NetworkService
import com.lastminute.repository.model.auth.LoginDto
import com.lastminute.repository.model.auth.SignupDto
import com.lastminute.repository.model.auth.UserDto

object AuthRepository : BaseRepository() {
    private val apiInterface = NetworkService.authApi

    suspend fun signup(body: SignupDto): UserDto? {
        return safeApiCall(
            call = { apiInterface.signup(body).await()},
            error = "signup fail"
        )
    }

    suspend fun login(body: LoginDto): UserDto? {
        return safeApiCall(
            call = { apiInterface.login(body).await()},
            error = "login fail"
        )
    }
}