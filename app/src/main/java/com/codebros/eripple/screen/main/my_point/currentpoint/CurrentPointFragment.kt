package com.codebros.eripple.screen.main.my_point.currentpoint

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentCurrentPointBinding
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.home.HomeFragment
import com.codebros.eripple.util.AccountInfoSingleton

class CurrentPointFragment : BaseFragment<CurrentPointViewModel, FragmentCurrentPointBinding>() {

    override val viewModel: CurrentPointViewModel by viewModels()

    override fun getViewBinding(): FragmentCurrentPointBinding =
        FragmentCurrentPointBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        viewModel.myPointLiveData.observe(this@CurrentPointFragment) {
            with(binding) {
                it?.let {

                    val myPoint = it["accountpoint_currentpoint"]
                    val settingExchangeStartPoint = it["setting_point_start_point"]

                    startExchangePointTxv.text = "${settingExchangeStartPoint}P 부터 환전이 가능합니다."
                    currentPointTxv.text = "${myPoint}P"

                    if (myPoint != null && settingExchangeStartPoint != null) {
                        val point = myPoint % settingExchangeStartPoint
                        val result = myPoint - point

                        possiblePointTxv.text = "${result}P"
                    } else {
                        possiblePointTxv.text = "0P"
                    }

                } ?: kotlin.run {
                    startExchangePointTxv.text = "0P 부터 환전이 가능합니다."
                    currentPointTxv.text = "0P"
                    possiblePointTxv.text = "0P"
                }


            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.getPointSituation(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CurrentPointFragment()

        const val TAG = "CurrentPointFragment"
    }

}