package com.codebros.eripple.screen.account.findpsw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityFindPasswordBinding
import com.codebros.eripple.screen.base.BaseActivity

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        initViews()
    }

    private fun initViews() = with(binding){
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


}