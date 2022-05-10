package com.codebros.eripple.widget.adapter.viewholder.bank

import com.codebros.eripple.data.url.DefaultUrl.SAMPLE_IMAGE_URL
import com.codebros.eripple.databinding.ViewholderSelectBankBinding
import com.codebros.eripple.extention.clear
import com.codebros.eripple.extention.load
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.bank.BankAdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class BankViewHolder(
    private val binding: ViewholderSelectBankBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<Bank>(binding, viewModel, customResourcesProvider) {

    override fun reset() {
        binding.bankIconImv.clear()
    }

    override fun bindViews(model: Bank, adapterListener: AdapterListener?) = with(binding) {
        if (adapterListener is BankAdapterListener) {
            root.setOnClickListener {
                adapterListener.onItemClick(model)
            }
        }
    }

    override fun bindData(model: Bank) = with(binding) {
        super.bindData(model)

        bankNameTxv.text = model.bank_name
        bankIconImv.load(SAMPLE_IMAGE_URL + "bank/" + model.bank_img)

    }

}