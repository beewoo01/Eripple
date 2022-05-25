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

    private var myPoint: Int? = null
    private var settingExchangeStartPoint: Int? = null
    private var appliedPoint: Int = 0
    private var possibleApplyPoint : Int = 0

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModel.applyExchangeLiveData.observe(this@ApplyExchangeFragment) {
            it?.let {
                myPoint = it["accountpoint_currentpoint"]
                // 현재 포인트
                settingExchangeStartPoint = it["setting_point_start_point"]
                // 환전 가능한 포인트

                if (myPoint != null && settingExchangeStartPoint != null) {
                    val point = myPoint!! % settingExchangeStartPoint!!
                    possibleApplyPoint = myPoint!! - point

                    binding.possibleStartPoint.text = "${settingExchangeStartPoint}P 부터 환전이 가능합니다."
                    binding.possiblePointTxv.text = "${possibleApplyPoint}P"
                }

            }
        }

        viewModel.applyExchangePointLiveData.observe(this@ApplyExchangeFragment) {
            it?.let {
                showToast("환전신청을 완료하였습니다.")
                myPoint = myPoint?.minus(appliedPoint)
                val point = myPoint!! % settingExchangeStartPoint!!
                possibleApplyPoint = myPoint!! - point
                binding.possiblePointTxv.text = "${possibleApplyPoint}P"

                binding.exchangeEdt.setText("")
            } ?: kotlin.run {
                showToast("환전신청을 실패하였습니다.")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.getPointSituation(it) }

    }

    override fun initViews() = with(binding) {
        super.initViews()

        accountOutBtn.setOnClickListener {
            isAvailability()
        }

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun isAvailability() {
        settingExchangeStartPoint?.let {
            if (myPoint != null && it <= binding.exchangeEdt.text.toString().toInt()) {
                if (binding.exchangeEdt.text.toString().isEmpty()) {

                    Toast.makeText(requireContext(), "환전할 포인트를 입력해 주세요", Toast.LENGTH_SHORT).show()

                } else if (
                    binding.exchangeEdt.text.toString().toInt() >
                    possibleApplyPoint
                ) {

                    showToast("환전가능한 포인트 이하로 신청해주세요.")

                } else if (binding.exchangeEdt.text.toString().toInt() >= it
                    && binding.exchangeEdt.text.toString().toInt() <= myPoint!!


                ) {
                    //환전 신청 ㄱㄱ
                    AccountInfoSingleton.account_idx?.let { idx ->
                        viewModel.applyExchangePoint(
                            idx,
                            binding.exchangeEdt.text.toString().toInt(),
                            it
                        )

                        appliedPoint += binding.exchangeEdt.text.toString().toInt()
                    }

                } else {
                    showToast("현재 보유하신 포인트 이하로 신청해주세요.")
                }
            } else {
                showToast("환전가능한 포인트가 없습니다.")
            }

        } ?: kotlin.run {
            showToast("현재는 환전이 불가합니다.")
        }


    }

}