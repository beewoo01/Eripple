package com.codebros.eripple.widget.adapter.viewholder.alarm

import com.codebros.eripple.databinding.ViewholderAlarmBinding
import com.codebros.eripple.model.alarm.AlarmModel
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class AlarmViewHolder(
    private val binding : ViewholderAlarmBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<AlarmModel>(binding, viewModel, customResourcesProvider){

    override fun reset() {

    }

    override fun bindViews(model: AlarmModel, adapterListener: AdapterListener?) {

    }

    override fun bindData(model: AlarmModel) = with(binding){
        super.bindData(model)

        erippleNameTxv.text = model.eripple_name
        contentTxv.text = model.alarm_content
        typeTxv.text = model.alarm_type
        dateTxv.text = model.alarm_createtime
    }


}