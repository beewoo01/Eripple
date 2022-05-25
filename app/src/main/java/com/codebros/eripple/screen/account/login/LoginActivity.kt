package com.codebros.eripple.screen.account.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.codebros.eripple.databinding.ActivityLoginBinding
import com.codebros.eripple.screen.account.findpsw.FindPasswordActivity
import com.codebros.eripple.screen.account.join.JoinActivity
import com.codebros.eripple.screen.account.join.termsofuse.TermsOfUseActivity
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.main.MainActivity
import com.codebros.eripple.util.AccountInfoSingleton
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging

class LoginActivity
    : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()

    //private var fcmToken : String? = null

    private val fcmToken by lazy {
        intent.getStringExtra("fcmToken")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*FirebaseMessaging.getInstance().token.addOnSuccessListener {
            fcmToken = it
        }*/


        //Log.wtf("deviceToken", )
        initViews()

    }

    override fun initViews() = with(binding) {
        loginTxv.setOnClickListener {
            /*startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()*/
            availability(emailEdt.text.toString(), pwsEdt.text.toString())
        }

        changePswTxv.setOnClickListener {
            startActivity(Intent(this@LoginActivity, FindPasswordActivity::class.java))
        }

        joinTxv.setOnClickListener {
            startActivity(Intent(this@LoginActivity, TermsOfUseActivity::class.java))
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
                viewModel.postLoginState(email, psw, fcmToken)
            }
        }

    }

    override fun observeData() {
        viewModel.loginState.observe(this@LoginActivity) { state ->
            state?.let {
                when {
                    it == 0 -> {
                        showToast("아이디 또는 비밀번호가 잘못되었습니다.")
                    }

                    it > 0 -> {
                        showToast("로그인에 성공하였습니다.")
                        if (binding.autoLoginRb.isChecked) {
                            setPreferences()
                        }
                        AccountInfoSingleton.getInstance(account_idx = it)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }

                    else -> {
                        showToast("네트워크에 오류가 있습니다.")
                    }
                }
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun setPreferences() {
        val prefernces = getSharedPreferences(PREFER_NAME, PREFERENCE_MODE)
        val editor = prefernces.edit()
        editor.putString(PREFER_ID, binding.emailEdt.text.toString())
        editor.putString(PREFER_PW, binding.pwsEdt.text.toString())
        editor.apply()
    }


    private fun showToast(msg: String) {
        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        val PREFERENCE_MODE = 1000
        val PREFER_NAME = "rebuild_preference"
        val PREFER_ID = "PREFER_ID"
        val PREFER_PW = "PREFER_PW"

    }


}