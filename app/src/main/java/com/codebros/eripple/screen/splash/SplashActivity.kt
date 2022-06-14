package com.codebros.eripple.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivitySplashBinding
import com.codebros.eripple.screen.account.login.LoginActivity
import com.codebros.eripple.screen.base.BaseActivity
import com.codebros.eripple.screen.main.MainActivity
import com.codebros.eripple.util.AccountInfoSingleton
import com.google.firebase.messaging.FirebaseMessaging

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val viewModel: SplashViewModel by viewModels()

    private var fcmToken: String? = null

    private var isAutoLogin : Boolean? = null
    private var isFinishLoading :Boolean? = null

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.loginState.observe(this@SplashActivity) {
            when {
                it == 0 -> {

                    isAutoLogin = false
                }

                it > 0 -> {
                    isAutoLogin = true
                    AccountInfoSingleton.getInstance(account_idx = it)
                }

                else -> {
                    isAutoLogin = false
                }

            }

            checkLoading()
        }
    }

    private fun checkLoading() {
        isFinishLoading?.let {
            if (it) {
                if (isAutoLogin != null) {
                    isAutoLogin?.let { auto ->
                        if (auto) {
                            moveAct(
                                Intent(this@SplashActivity, MainActivity::class.java)
                            )
                        } else {
                            moveAct(
                                Intent(this@SplashActivity, LoginActivity::class.java).putExtra(
                                    "fcmToken",
                                    fcmToken
                                )
                            )
                        }
                    }
                }

            }
        }

    }

    private fun showToast(msg : String){
        Toast.makeText(this@SplashActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun initViews() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            fcmToken = it
        }
        fadeOutAnimation.setAnimationListener(fadeOutAnimationListener)
        val fadeInAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.logo_fade_in)
        fadeInAnimation.setAnimationListener(fadeInAnimationListener)

        binding.logoImv.startAnimation(fadeInAnimation)
        checkAutoLogin()

    }

    private fun checkAutoLogin() {
        val pref = getSharedPreferences(LoginActivity.PREFER_NAME, LoginActivity.PREFERENCE_MODE)
        val email = pref.getString(LoginActivity.PREFER_ID, null)
        val pw = pref.getString(LoginActivity.PREFER_PW, null)

        if (!email.isNullOrEmpty() && !pw.isNullOrEmpty()) {
            viewModel.autoLogin(email, pw, fcmToken)
        } else {
            isAutoLogin = false
            checkLoading()
            /*moveAct(
                Intent(this@SplashActivity, LoginActivity::class.java).putExtra(
                    "fcmToken",
                    fcmToken
                )
            )*/
        }
    }

    private fun moveAct(intent: Intent) {
        startActivity(intent)
        finish()
    }

    private val fadeOutAnimation by lazy {
        AnimationUtils.loadAnimation(this@SplashActivity, R.anim.logo_fade_out)
    }

    private val fadeOutAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) = Unit

        override fun onAnimationEnd(p0: Animation?) {
            binding.logoImv.visibility = View.GONE

            isFinishLoading = true
            if (isAutoLogin != null) {
                checkAutoLogin()
            }
        }

        override fun onAnimationRepeat(p0: Animation?) = Unit

    }

    private val fadeInAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) = Unit

        override fun onAnimationEnd(p0: Animation?) {
            binding.logoImv.startAnimation(fadeOutAnimation)
        }

        override fun onAnimationRepeat(p0: Animation?) = Unit

    }

}