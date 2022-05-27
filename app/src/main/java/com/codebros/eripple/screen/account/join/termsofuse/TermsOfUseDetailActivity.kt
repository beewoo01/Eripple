package com.codebros.eripple.screen.account.join.termsofuse

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityTermsOfUseDetailBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

class TermsOfUseDetailActivity : AppCompatActivity() {

    private val binding: ActivityTermsOfUseDetailBinding by lazy {
        ActivityTermsOfUseDetailBinding.inflate(layoutInflater)
    }

    private val state by lazy {
        intent.getIntExtra("state", 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        setFile()
    }

    private fun initView() = with(binding) {

        if (state == 0) {

            titleTxv.text = "이용약관"
            topTxv.text = "이용약관"
        } else {

            titleTxv.text = "개인정보처리방침"
            topTxv.text = "개인정보처리방침"

        }

        contentTxv.movementMethod = ScrollingMovementMethod()

        backImb.setOnClickListener {
            finish()
        }

    }

    private fun setFile() {

        try {

            val text = if (state == 0) {
                application.assets.open("personal_Info").bufferedReader().use {
                    it.readText()
                }
            } else {
                application.assets.open("termsofservice").bufferedReader().use {
                    it.readText()
                }
            }

            binding.contentTxv.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }catch (e : Exception) {
            e.printStackTrace()
        }


    }


}