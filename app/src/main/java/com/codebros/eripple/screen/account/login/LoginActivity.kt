package com.codebros.eripple.screen.account.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivityLoginBinding
import com.codebros.eripple.screen.base.BaseActivity

class LoginActivity :
    BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

    }

    override fun initViews() = with(binding) {
        loginTxv.setOnClickListener {
            availability(emailEdt.text.toString(), pwsEdt.text.toString())
        }
    }

    private fun availability(email: String, psw: String) {

        when {

            email.isEmpty() -> {
                showToast("이메일을 입력해주세요")
            }

            psw.isEmpty() -> {
                showToast("비밀번호를 입력해주세요")
            }

            else -> {
                viewModel.postLoginState(email, psw)
            }
        }

    }

    override fun observeData() {
        viewModel.loginState.observe(this@LoginActivity) { state ->
            state?.let {

            }
        }
    }


    private fun showToast(msg: String) {
        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
    }


}