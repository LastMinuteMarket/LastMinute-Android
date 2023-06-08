package com.lastminute.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lastminute.common.BaseActivity
import com.lastminute.ui.R
import com.lastminute.ui.databinding.ActivityLoginBinding
import com.lastminute.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity() : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                binding.etLoginId.text.toString(),
                binding.etLoginPassword.text.toString()
            )
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}