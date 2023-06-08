package com.lastminute.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastminute.repository.model.auth.SignupDto
import com.lastminute.repository.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    private val authRepository = AuthRepository

    fun signup(nickname: String, password: String, email: String) {
        val body = SignupDto(nickname = nickname, password = password, email = email)

        viewModelScope.launch {
            authRepository.signup(body)
        }
    }

}