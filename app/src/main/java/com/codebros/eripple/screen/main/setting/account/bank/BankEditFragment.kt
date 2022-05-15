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
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.account.bank.SelectBankActivity
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton

class BankEditFragment : BaseFragment<BankEditViewModel, FragmentBankEditBinding>() {

    override val viewModel: BankEditViewModel by viewModels()

    override fun getViewBinding(): FragmentBankEditBinding =
        FragmentBankEditBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.bankAccountNumberLiveData.observe(this@BankEditFragment) {
            binding.accountNumberEdit.setText(it ?: "")
        }

        viewModel.registerAccountBank.observe(this@BankEditFragment) {
            it?.let { result ->
                when(result) {
                    0 -> {
                        showToast("계좌등록에 성공하였습니다.")
                        requireActivity().onBackPressed()
                    }

                    1 -> {
                        showToast("계좌수정에 성공하였습니다.")
                        requireActivity().onBackPressed()
                    }

                    -1 -> {
                        showToast("계좌정보 변경에 실패하였습니다.")
                    }
                }
            }
        }
    }

    private var paramModel: Bank? = null

    private val startActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { it ->
        if (it.resultCode == RESULT_OK) {
            paramModel = it.data?.getParcelableExtra("model")
            paramModel?.let { model ->
                showToast("${model.bank_name}을 선택하였습니다.")
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.getAccountBank(it) }
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

                else -> {

                    paramModel?.let {

                        if (it.bank_idx < 1) {
                            showToast("은행을 선택해주세요.")
                            return@setOnClickListener
                        }

                        AccountInfoSingleton.account_idx?.let { it1 ->
                            viewModel.registerAccountBank(
                                it1,
                                it.bank_idx,
                                accountNumberEdit.text.toString()
                            )
                        }


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