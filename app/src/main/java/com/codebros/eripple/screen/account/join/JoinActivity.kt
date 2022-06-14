package com.codebros.eripple.screen.account.join

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivityJoinBinding
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.account.bank.SelectBankActivity2
import com.codebros.eripple.screen.base.BaseActivity


class JoinActivity : BaseActivity<JoinViewModel, ActivityJoinBinding>() {

    override val viewModel: JoinViewModel by viewModels()

    override fun getViewBinding(): ActivityJoinBinding = ActivityJoinBinding.inflate(layoutInflater)

    private val pattern = android.util.Patterns.EMAIL_ADDRESS

    private var paramModel: Bank? = null
    private var bank_idx: Int = 0

    private var fixName = ""
    private var fixphone = ""

    private val spinnerItems = arrayOf("gmail.com", "naver.com", "daum.net", "nate.com")

    override fun initViews() = with(binding) {

        val intent = intent
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")

        if (!name.isNullOrEmpty()) {
            fixName = intent.getStringExtra("name").toString()
            nameEdt.setText(name)

        }
        if (!phone.isNullOrEmpty()) {
            fixphone = intent.getStringExtra("phone").toString()
            phoneEdt.setText(phone)
        }

        nameEdt.isEnabled = false
        phoneEdt.isEnabled = false

        joinBtn.setOnClickListener {
            availability()
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        selectBankTxv.setOnClickListener {
            getResult.launch(Intent(this@JoinActivity, SelectBankActivity2::class.java))
        }

        val adapter = ArrayAdapter(
            this@JoinActivity, android.R.layout.simple_spinner_item, spinnerItems
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        //adapter.

        mailSubSpn.adapter = adapter
        mailSubSpn.onItemSelectedListener = onSelectedSpinnerItemListener

    }

    private val getResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                paramModel = it.data?.getParcelableExtra("model")
                paramModel?.let { model ->
                    showToast("${model.bank_name}을 선택하였습니다.")
                }
            }
        }


    private fun availability() = with(binding) {
        if (nameEdt.text.toString().isEmpty()) {
            showToast("이름을 입력해주세요.")

        } else if (passwordEdt.text.toString().isEmpty()
            || passwordEdt.text.toString().length < 8
        ) {

            showToast("비밀번호를 8자 이상 등록해주세요.")

        } else if (passwordEdt.text.toString() != passwordCheckEdt.text.toString()) {

            showToast("비밀번호가 일치하지 않습니다.")

        } else if (emailEdt.text.toString().isEmpty()
            || !pattern.matcher(emailEdt.text.toString() + "@${mailSubSpn.selectedItem}").matches()
        ) {

            showToast("이메일을 정확히 입력해주세요.")

        } else {
            var bankAccount = "0"
            paramModel?.let {
                bank_idx = it.bank_idx
            } ?: kotlin.run {
                bankAccount = "0"
            }
            bankAccount = bankAccountEdt.text.toString().ifEmpty {
                bank_idx = 0
                "0"
            }

            if (paramModel == null || bankAccountEdt.text.toString().isEmpty()) {
                bank_idx = 0
                bankAccount = "0"
            }


            viewModel.postJoinState(
                nameEdt.text.toString(),
                phoneEdt.text.toString(),
                passwordEdt.text.toString(),
                emailEdt.text.toString() + "@${mailSubSpn.selectedItem}",
                bankAccount,
                bank_idx
            )
        }

    }

    private val onSelectedSpinnerItemListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            (p0?.getChildAt(0) as TextView).setTextColor(
                ContextCompat.getColor(
                    this@JoinActivity,
                    R.color.defaultBlackTextColor
                )
            )
        }

        override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    }


    override fun observeData() {

        viewModel.joinState.observe(this@JoinActivity) { result ->

            when (result) {
                null -> {
                    showToast("회원가입에 실패하였습니다.")
                }

                -1 -> {
                    showToast("이미 가입된 메일입니다.")
                }

                0 -> {
                    showToast("회원가입에 성공하였습니다.")
                    finish()
                }

                1 -> {
                    showToast("이미 가입된 사용자입니다.")
                }

                else -> {
                    showToast("회원가입에 실패하였습니다.")
                }

                /*1 -> {
                    showToast("회원가입에 성공하였습니다.")
                    finish()
                }
                -1 -> {
                    showToast("이미 가입된 전화번호이거나 이메일 입니다.")
                }
                -2 -> {
                    showToast("회원가입에 실패하였습니다.")
                }*/
            }

        }

    }


    private fun showToast(msg: String) {
        Toast.makeText(this@JoinActivity, msg, Toast.LENGTH_SHORT).show()
    }


}