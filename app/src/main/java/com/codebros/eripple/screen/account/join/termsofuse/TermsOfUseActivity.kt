package com.codebros.eripple.screen.account.join.termsofuse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityTermsOfUseBinding

class TermsOfUseActivity : AppCompatActivity() {

    private val binding: ActivityTermsOfUseBinding by lazy {
        ActivityTermsOfUseBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

    }

    private fun initView() = with(binding) {

        allAgreeCheckBox.setOnClickListener {
            checkBoxClick(0)
        }

        termsOfUseCheckbox.setOnClickListener {
            checkBoxClick(1)
        }

        personalInformationCheckbox.setOnClickListener {
            checkBoxClick(2)
        }



        detailTermsOfUseImb.setOnClickListener {
            moveDetail(0)
        }

        detailPersonalInfoImb.setOnClickListener {
            moveDetail(1)
        }

        backImb.setOnClickListener {
            finish()
        }

    }

    private fun checkBoxClick(state: Int) = with(binding) {

        if (state == 0 && allAgreeCheckBox.isChecked) {
            termsOfUseCheckbox.isChecked = true
            personalInformationCheckbox.isChecked = true

        } else if (state == 0 && !allAgreeCheckBox.isChecked) {
            termsOfUseCheckbox.isChecked = false
            personalInformationCheckbox.isChecked = false
        } else {
            allAgreeCheckBox.isChecked =
                (termsOfUseCheckbox.isChecked && personalInformationCheckbox.isChecked)
        }

        gotoAuthBtn.isEnabled = allAgreeCheckBox.isChecked

        gotoAuthBtn.background =

            if (allAgreeCheckBox.isChecked) {
                ContextCompat.getDrawable(
                    this@TermsOfUseActivity,
                    R.drawable.bg_turms_of_use_checkbox
                )
            } else {
                ContextCompat.getDrawable(
                    this@TermsOfUseActivity,
                    R.drawable.bg_turms_of_use_checkbox
                )
            }

    }


    private fun moveDetail(param: Int) {

        startActivity(
            Intent(this@TermsOfUseActivity, TermsOfUseDetailActivity::class.java)
                .apply {
                    putExtra("state", param)
                }

        )

    }


}