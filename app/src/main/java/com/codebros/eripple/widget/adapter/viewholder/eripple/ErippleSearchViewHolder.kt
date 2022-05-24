package com.codebros.eripple.widget.adapter.viewholder.eripple

import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderSearchErippleBinding
import com.codebros.eripple.extention.clear
import com.codebros.eripple.extention.localLoad
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class ErippleSearchViewHolder(
    private val binding: ViewholderSearchErippleBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<Eripple>(binding, viewModel, customResourcesProvider) {

    override fun reset() {
        binding.bookmarkImb.clear()
    }

    override fun bindData(model: Eripple) = with(binding) {

        erippleNameTxv.text = model.eripple_name
        erippleAddressTxv.text =
            model.eripple_address_detail?.let {
                "${model.eripple_address} $it"

            } ?: kotlin.run {
                model.eripple_address
            }

        when(model.eripple_status) {
            3, 4 -> {
                stateTxv.run {
                    background = customResourcesProvider.getDrawable(R.drawable.bg_full_green)
                    text = "사용 가능"
                }
            }

            else -> {
                stateTxv.run {
                    background = customResourcesProvider.getDrawable(R.drawable.bg_full_gray)
                    text = "사용 불가"
                }
            }
        }

        val drawable = if (model.bookmark_idx > 0) {
            customResourcesProvider.getDrawable(R.drawable.ic_full_heart_location)
        } else {
            customResourcesProvider.getDrawable(R.drawable.ic_empty_heart_location)
        }

        bookmarkImb.localLoad(drawable)

    }

    override fun bindViews(model: Eripple, adapterListener: AdapterListener?) {
        with(binding) {

            if (adapterListener is ErippleAdapterListener) {

                root.setOnClickListener {
                    adapterListener.onItemClick(model)
                }

                bookmarkImb.setOnClickListener {
                    adapterListener.onHeartClick(model)
                }

                shareTxv.setOnClickListener {
                    adapterListener.onShearClick(model)
                }
            }

        }
    }
}