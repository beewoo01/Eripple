package com.codebros.eripple.screen.main.setting.account.bank

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ActivitySelectBankBinding
import com.codebros.eripple.databinding.FragmentBankEditBinding
import com.codebros.eripple.screen.account.bank.SelectBankActivity
import com.codebros.eripple.screen.base.BaseFragment

class BankEditFragment : BaseFragment<BankEditViewModel, FragmentBankEditBinding>() {

    override val viewModel: BankEditViewModel by viewModels()

    override fun getViewBinding(): FragmentBankEditBinding =
        FragmentBankEditBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.bankAccountNumberLiveData.observe(this@BankEditFragment) {
            binding.accountNumberEdit.setText(it ?: "")
        }
    }

    private var bankIdx: Int? = null

    private val startActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            bankIdx = it.data?.getIntExtra("bank_idx", 0)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAccountBank(1)
    }

    override fun initViews() = with(binding) {
        super.initViews()

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        selectBankTxv.setOnClickListener {
            startActivityResult.launch(Intent(requireActivity(), SelectBankActivity::class.java))
        }

        editBtn.setOnClickListener {

            when {

                accountNumberEdit.text.toString().isEmpty() -> {
                    showToast("계좌번호를 정확히 입력해주세요.")
                }

                bankIdx == null || bankIdx == 0 -> {
                    showToast("은행을 선택해주세요.")
                }

                else -> {
                    // TODO: Change OR Insert Bank
                    bankIdx?.let {
                        viewModel.registerAccountBank(1, it, accountNumberEdit.text.toString())
                    } ?: kotlin.run {
                        showToast("은행을 선택해주세요.")
                    }

                }
            }

        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}