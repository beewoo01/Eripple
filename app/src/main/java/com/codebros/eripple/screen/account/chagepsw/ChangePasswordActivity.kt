package com.codebros.eripple.screen.account.chagepsw

import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivityChangePasswordBinding
import com.codebros.eripple.screen.base.BaseActivity

class ChangePasswordActivity :
    BaseActivity<ChangePasswordViewModel, ActivityChangePasswordBinding>() {

    override val viewModel: ChangePasswordViewModel by viewModels()

    override fun getViewBinding(): ActivityChangePasswordBinding =
        ActivityChangePasswordBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun initViews() {
        super.initViews()
    }

}