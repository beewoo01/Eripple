package com.codebros.eripple.widget.adapter.viewholder.notice

import androidx.core.view.isVisible
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderNoticeBinding
import com.codebros.eripple.extention.localLoad
import com.codebros.eripple.model.notice.Notice
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.notice.NoticeAdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class NoticeViewHolder(
    private val binding: ViewholderNoticeBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<Notice>(binding, viewModel, customResourcesProvider) {

    override fun reset() = Unit

    override fun bindViews(model: Notice, adapterListener: AdapterListener?) = with(binding) {
        moreImb.setOnClickListener {
            contentGroup.isVisible = !contentGroup.isVisible
            if (contentGroup.isVisible) {
                moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_arrow_up))
            } else {
                moreImb.localLoad(customResourcesProvider.getDrawable(R.drawable.ic_bottom_icon))
            }
        }

        root.setOnClickListener {
            if (adapterListener is NoticeAdapterListener) {
                adapterListener.onItemClick(model)
            }
        }
    }

    override fun bindData(model: Notice) = with(binding) {
        super.bindData(model)

        titleTxv.text = model.notice_title
        contentTxv.text = model.notice_contents


    }


}