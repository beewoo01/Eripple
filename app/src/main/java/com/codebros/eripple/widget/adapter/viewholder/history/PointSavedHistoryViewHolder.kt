package com.codebros.eripple.widget.adapter.viewholder.history

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderHistoryPointBinding
import com.codebros.eripple.extention.load
import com.codebros.eripple.extention.localLoad
import com.codebros.eripple.model.pointsavedhistory.PointSavedHistory
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class PointSavedHistoryViewHolder(
    private val binding : ViewholderHistoryPointBinding,
    viewModel : BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<PointSavedHistory>(binding, viewModel, customResourcesProvider){

    override fun reset() {

    }

    @SuppressLint("SetTextI18n")
    override fun bindData(model: PointSavedHistory) = with(binding){
        Log.wtf("PointSavedHistoryViewHolder", "bindData")
        erippleNameTxv.text = model.eripple_name
        erippleAddressTxv.text = model.eripple_address_detail?.let {
            "${model.eripple_address} $it"
        } ?: kotlin.run {
            model.eripple_address
        }

        erippleDateTxv.text = model.waste_discharge_record_updatetime

        collectAmountTxv.text = "${String.format("%.1f", model.waste_discharge_record_gram)}g"
        savingPointTxv.text = "${model.waste_discharge_record_earned_points}P"

    }

    override fun bindViews(model: PointSavedHistory, adapterListener: AdapterListener?) = with(binding){
        Log.wtf("PointSavedHistoryViewHolder", "bindViews")

        moreImb.setOnClickListener {
            model.viewStatus = !model.viewStatus
            Log.wtf("moreImb.setOnClickListener", "model.viewStatus ${model.viewStatus}")

            if (model.viewStatus) {
                moreView.isVisible = true
                moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_arrow_up))
            } else {
                moreView.isVisible = false
                moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_bottom_icon))
            }


        }

        if (model.viewStatus) {
            moreView.isVisible = true
            moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_arrow_up))
        } else {
            moreView.isVisible = false
            moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_bottom_icon))
        }
    }


}