package com.codebros.eripple.widget.adapter.viewholder.bookmark

import android.content.Context
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.codebros.eripple.R
import com.codebros.eripple.databinding.ViewholderHomeBookmarkBinding
import com.codebros.eripple.extention.clear
import com.codebros.eripple.extention.load
import com.codebros.eripple.extention.localLoad
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.bookmark.SimpleErippleInfoWithBookmarkListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder
import kotlin.coroutines.coroutineContext

class SimpleErippleInfoWithBookmarkViewHolder(
    private val binding: ViewholderHomeBookmarkBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<SimpleErippleInfoWithBookmark>(binding, viewModel, customResourcesProvider) {

    override fun reset() = with(binding) {
        deapImb.clear()
    }

    override fun bindData(model: SimpleErippleInfoWithBookmark) {
        super.bindData(model)
        with(binding) {
            bookmarkTxv.text = model.eripple_name

            customResourcesProvider.getDrawable(R.drawable.ic_full_heart)?.let {
                deapImb.localLoad(it)
            }


        }
    }

    override fun bindViews(model: SimpleErippleInfoWithBookmark, adapterListener: AdapterListener) {
        with(binding) {
            if (adapterListener is SimpleErippleInfoWithBookmarkListener) {
                root.setOnClickListener {
                    adapterListener.onClickItem(model)
                }
            }
        }
    }

}