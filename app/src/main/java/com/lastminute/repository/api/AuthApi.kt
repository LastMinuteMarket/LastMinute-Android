package com.lastminute.repository.api

import com.lastminute.repository.model.auth.LoginDto
import com.lastminute.repository.model.auth.SignupDto
import com.lastminute.repository.model.auth.UserDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/users/signup")
    fun signup(
        @Body body: SignupDto
    ): Deferred<Response<UserDto>>

    @POST("api/users/login")
    fun login(
        @Body body: LoginDto
    ): Deferred<Response<UserDto>>
}