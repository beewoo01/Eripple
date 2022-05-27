package com.codebros.eripple.screen.account.findpsw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codebros.eripple.databinding.ActivityFindPasswordBinding
import com.codebros.eripple.screen.account.join.niceapi.NiceApiActivity

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding){
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        authTxv.setOnClickListener {
            startActivity(Intent(this@FindPasswordActivity, NiceApiActivity::class.java).apply {
                putExtra("requester", 1)
            })
            finish()
        }
    }


}