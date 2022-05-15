package com.codebros.eripple.screen.main.my_point.appliyexchage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentApplyExchangeBinding
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton

class ApplyExchangeFragment : BaseFragment<ApplyExchangeViewModel, FragmentApplyExchangeBinding>() {

    override val viewModel: ApplyExchangeViewModel by viewModels()

    override fun getViewBinding(): FragmentApplyExchangeBinding =
        FragmentApplyExchangeBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModel.applyExchangeLiveData.observe(this@ApplyExchangeFragment) {
            it?.let {
                val myPoint = it["accountpoint_currentpoint"]
                // 현재 포인트
                val settingExchangeStartPoint = it["setting_point_start_point"]
                // 환전 가능한 포인트

                if (myPoint != null && settingExchangeStartPoint != null) {
                    val point = myPoint % settingExchangeStartPoint
                    val result = myPoint - point

                    binding.possibleStartPoint.text = "${settingExchangeStartPoint}P 부터 환전이 가능합니다."
                    binding.possiblePointTxv.text = "${result}P"
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.getPointSituation(it) }

    }

    override fun initViews() = with(binding){
        super.initViews()

        accountOutBtn.setOnClickListener {
            isAvailability()
        }

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun isAvailability() {
        if (binding.exchangeEdt.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "환전할 포인트를 입력해 주세요", Toast.LENGTH_SHORT).show()
        } else {
            //환전 신청 ㄱㄱ

        }

    }

}