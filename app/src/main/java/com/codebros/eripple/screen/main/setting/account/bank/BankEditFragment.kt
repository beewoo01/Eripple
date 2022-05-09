package com.codebros.eripple.screen.main.setting.account.bank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentBankEditBinding
import com.codebros.eripple.screen.base.BaseFragment

class BankEditFragment : BaseFragment<BankEditViewModel, FragmentBankEditBinding>() {

    override val viewModel: BankEditViewModel by viewModels()

    override fun getViewBinding(): FragmentBankEditBinding =
        FragmentBankEditBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    private var bankIdx : Int? = null

    override fun initViews() = with(binding) {
        super.initViews()

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        joinBtn.setOnClickListener {
            when {

                accountNumberEdit.text.toString().isEmpty() -> {
                    showToast("계좌번호를 정확히 입력해주세요.")
                }

                bankIdx == null -> {
                    showToast("은행을 선택해주세요.")
                }

                else -> {
                    // TODO: Change OR Insert Bank

                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}