package com.codebros.eripple.widget.adapter.viewholder.exchange

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.isVisible
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderExchangeHistoryBinding
import com.codebros.eripple.model.exchange.AccountExchangeHistory
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class ExchangeViewHolder(
    private val binding: ViewholderExchangeHistoryBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<AccountExchangeHistory>(binding, viewModel, customResourcesProvider) {

    override fun reset() {

    }

    @SuppressLint("SetTextI18n")
    override fun bindData(model: AccountExchangeHistory) = with(binding) {

        pointTxv.text = "${model.exchange_history_applier_point}P"
        dateTxv.text = model.exchange_history_createtime
        when (model.exchange_history_status) {

            0 -> {
                exchangeStatusTxv.setTextColor(customResourcesProvider.getColor(R.color.defaultEditHintColor))
                exchangeStatusTxv.text = "환전 대기중"
                moreGroup.isVisible = false
                moreImb.isVisible = false
            }

            1 -> {
                exchangeStatusTxv.setTextColor(customResourcesProvider.getColor(R.color.subTextColor))
                exchangeStatusTxv.text = "환전완료"
                moreGroup.isVisible = false
                moreImb.isVisible = false
            }

            2 -> {
                exchangeStatusTxv.setTextColor(customResourcesProvider.getColor(R.color.defaultEditHintColor))
                exchangeStatusTxv.text = "환전취소"
                cancelCauseTxv.text = model.exchange_cancel_couse
                moreImb.isVisible = true
            }

        }

        moreImb.setOnClickListener {
            moreGroup.isVisible = !moreGroup.isVisible
        }

    }

    override fun bindViews(model: AccountExchangeHistory, adapterListener: AdapterListener?) {


    }


}