package com.codebros.eripple.widget.adapter.viewholder.bookmark

import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderBookmarkBinding
import com.codebros.eripple.databinding.ViewholderHomeBookmarkBinding
import com.codebros.eripple.extention.localLoad
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class BookMarkViewHolder(
    private val binding: ViewholderBookmarkBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
): ModelViewHolder<SimpleErippleInfoWithBookmark>(binding, viewModel, customResourcesProvider){

    override fun reset() {

    }

    override fun bindViews(
        model: SimpleErippleInfoWithBookmark,
        adapterListener: AdapterListener?
    ) {

    }

    override fun bindData(model: SimpleErippleInfoWithBookmark) = with(binding){
        super.bindData(model)

        bookmarkTxv.text = model.eripple_name
        customResourcesProvider.getDrawable(R.drawable.ic_full_heart)?.let {
            deapImb.localLoad(it)
        }

        when (model.eripple_status) {

            3, 4 -> {
                bookmarkStateTxv.text = "사용가능"
            }

            else -> {
                bookmarkStateTxv.text = "사용불가"
            }
        }

    }
}