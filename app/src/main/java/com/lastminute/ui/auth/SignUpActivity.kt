package com.lastminute.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lastminute.common.BaseActivity
import com.lastminute.ui.R
import com.lastminute.ui.databinding.ActivitySignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity() : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signupViewModel: SignupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSignup.setOnClickListener {
            signupViewModel.signup(
                nickname = binding.etLoginId.text.toString(),
                password = binding.etLoginPassword.text.toString(),
                email = binding.etEmail.text.toString()
            )
            finish()
        }
    }
}