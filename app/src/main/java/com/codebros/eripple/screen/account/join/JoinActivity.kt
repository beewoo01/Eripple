package com.codebros.eripple.screen.account.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityJoinBinding
import com.codebros.eripple.screen.base.BaseActivity
import java.util.regex.Pattern

class JoinActivity : BaseActivity<JoinViewModel, ActivityJoinBinding>() {

    override val viewModel: JoinViewModel by viewModels()

    override fun getViewBinding(): ActivityJoinBinding = ActivityJoinBinding.inflate(layoutInflater)

    private val pattern = android.util.Patterns.EMAIL_ADDRESS


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initViews() = with(binding) {

        joinBtn.setOnClickListener {
            availability()
        }

    }

    private fun availability() = with(binding) {
        if (passwordEdt.text.toString().isEmpty()
            || passwordEdt.text.toString().length < 8
        ) {

            showToast("비밀번호를 8자 이상 등록해주세요.")

        } else if (passwordEdt.text.toString() != passwordCheckEdt.text.toString()) {

            showToast("비밀번호가 일치하지 않습니다.")

        } else if (emailEdt.text.toString().isEmpty()
            || !pattern.matcher(emailEdt.text.toString()).matches()
        ) {

            showToast("이메일을 정확히 입력해주세요.")

        } else {

            viewModel.postJoinState(
                nameEdt.text.toString(),
                phoneEdt.text.toString(),
                passwordCheckEdt.text.toString(),
                emailEdt.text.toString()
            )
        }

    }


    override fun observeData() {

        viewModel.joinState.observe(this@JoinActivity) { result ->

            when (result) {
                null -> {
                    showToast("회원가입에 실패하였습니다.")
                }
                1 -> {
                    showToast("회원가입에 성공하였습니다.")
                    finish()
                }
                -1 -> {
                    showToast("이미 가입된 전화번호이거나 이메일 입니다.")
                }
                -2 -> {
                    showToast("회원가입에 실패하였습니다.")
                }
            }

        }

    }


    private fun showToast(msg: String) {
        Toast.makeText(this@JoinActivity, msg, Toast.LENGTH_SHORT).show()
    }


}