package com.codebros.eripple.screen.account.join.termsofuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
        titleTxv.text = "이용약관"
        topTxv.text = "이용약관"
        contentTxv.movementMethod = ScrollingMovementMethod()

        backImb.setOnClickListener {
            finish()
        }


    }

}