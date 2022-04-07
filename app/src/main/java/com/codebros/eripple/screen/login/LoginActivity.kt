package com.codebros.eripple.screen.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityLoginBinding
import com.codebros.eripple.databinding.ActivitySampleBinding
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.sample.SampleViewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun observeData() {

    }
}