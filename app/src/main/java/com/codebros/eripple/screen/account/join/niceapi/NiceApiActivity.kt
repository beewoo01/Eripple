package com.codebros.eripple.screen.account.join.niceapi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityNiceApiBinding
import com.codebros.eripple.screen.account.chagepsw.ChangePasswordActivity
import com.codebros.eripple.screen.account.join.JoinActivity

class NiceApiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNiceApiBinding
    private var requester = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNiceApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    private fun initViews() = with(binding){
        requester = intent.getIntExtra("requester", 0)
        webView.apply {
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(), "Android")
            webViewClient = MyWebViewClient()
            //loadUrl("https://melatopia.me/eripple_mobile/getSecretToken")
            loadUrl("http://codebrosdev.cafe24.com:8080/eripple_mobile/getSecretToken")

        }
    }

    private fun moveAct(mobileno : String, name : String){
        if (requester == 0) {
            startActivity(Intent(this@NiceApiActivity, JoinActivity::class.java).apply {
                putExtra("name", name)
                putExtra("phone", mobileno)
            })
        } else {
            startActivity(Intent(this@NiceApiActivity, ChangePasswordActivity::class.java).apply {
                putExtra("name", name)
                putExtra("phone", mobileno)
            })
        }

        finish()
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = Uri.parse(request?.url?.host).toString()
            //return url.startsWith("https://melatopia.me/eripple_mobile/")
            return url.startsWith("http://codebrosdev.cafe24.com:8080/eripple_mobile/")

            //return super.shouldOverrideUrlLoading(view, request)
        }
    }

    inner class WebAppInterface() {
        @JavascriptInterface
        fun getNiceData(resultcode : String, mobileno: String, name : String) {
            Log.wtf("WebAppInterface", "getNiceData")
            Log.wtf("resultcode", resultcode.toString())
            Log.wtf("mobileno", mobileno.toString())
            Log.wtf("name", name.toString())
            if (resultcode == "0000") {
                moveAct(mobileno, name)
            } else {
                Toast.makeText(this@NiceApiActivity, "서버 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}