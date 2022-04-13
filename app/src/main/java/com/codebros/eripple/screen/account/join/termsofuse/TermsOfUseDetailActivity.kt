package com.codebros.eripple.screen.account.join.termsofuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityTermsOfUseDetailBinding

class TermsOfUseDetailActivity : AppCompatActivity() {

    private val binding : ActivityTermsOfUseDetailBinding by lazy {
        ActivityTermsOfUseDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding){

        val param = intent.getIntExtra("state", 0)

        if (param == 0) {

            titleTxv.text = "이용약관"

        } else {

            titleTxv.text = "개인정보처리방침"

        }

        backImb.setOnClickListener {
            finish()
        }

        okBtn.setOnClickListener {
            finish()
        }


    }

}