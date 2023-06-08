package com.lastminute.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.repository.NetworkService
import com.lastminute.repository.model.auth.LoginDto
import com.lastminute.repository.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository

    fun login(nickname: String, password: String) {
        val body = LoginDto(nickname = nickname, password = password)

        viewModelScope.launch {
            val user = authRepository.login(body)
            if (user != null)
                NetworkService.userId = user.userId
        }
    }
}