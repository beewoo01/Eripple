package com.codebros.eripple.screen.account.chagepsw

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivityChangePasswordBinding
import com.codebros.eripple.screen.base.BaseActivity

class ChangePasswordActivity :
    BaseActivity<ChangePasswordViewModel, ActivityChangePasswordBinding>() {

    override val viewModel: ChangePasswordViewModel by viewModels()

    override fun getViewBinding(): ActivityChangePasswordBinding =
        ActivityChangePasswordBinding.inflate(layoutInflater)

    private var account_idx: Int? = null

    override fun observeData() {
        viewModel.accountState.observe(this@ChangePasswordActivity) {
            it?.let {

                if (it > 0) {
                    account_idx = it
                    binding.nextBtn.isEnabled = true

                } else {
                    showToast("아이디를 찾을수없습니다.")
                    Log.wtf("initViews name", "1111111")
                    //finish()
                }
            } ?: kotlin.run {
                showToast("아이디를 찾을수없습니다.")
                finish()
            }
        }

        viewModel.changePswState.observe(this@ChangePasswordActivity) {
            it?.let {
                if (it > 0) {
                    showToast("비밀번호를 변경하였습니다.")
                    finish()
                } else {
                    showToast("비밀번호 변경을 실패하였습니다.")
                }
            } ?: kotlin.run {
                showToast("비밀번호를 변경을 실패하였습니다.")
            }
        }
    }

    override fun initViews() {
        Log.wtf("ChangePasswordActivity", "initViews")
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        Log.wtf("initViews name", name)
        Log.wtf("initViews phone", phone)
        if (name.isNullOrEmpty() || phone.isNullOrEmpty()) {
            //finish()
            showToast("아이디를 찾을수없습니다.")
        } else {
            viewModel.getAccountInfoForChangePsw(phone, name)
        }

        binding.nextBtn.setOnClickListener {
            vaildation()
        }

    }

    private fun vaildation() = with(binding) {

        if (account_idx == null || account_idx == 0) {
            showToast("아이디를 찾을수없습니다.")
            finish()
        }

        if (passwordEdt.text.toString().isEmpty()
            || passwordEdt.text.toString().length < 8
        ) {
            showToast("비밀번호를 8자 이상 등록해주세요.")
        } else if (passwordEdt.text.toString() != confirmPasswordEdt.text.toString()) {

            showToast("비밀번호가 일치하지 않습니다.")

        } else {

            account_idx?.let {

                viewModel.changePsw(passwordEdt.text.toString(), it)

            } ?: kotlin.run {
                showToast("아이디를 찾을수없습니다.")
                finish()
            }
        }


    }


    private fun showToast(msg: String) {
        Toast.makeText(this@ChangePasswordActivity, msg, Toast.LENGTH_SHORT).show()
    }

}