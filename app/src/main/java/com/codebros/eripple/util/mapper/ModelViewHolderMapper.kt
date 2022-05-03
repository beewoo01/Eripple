package com.codebros.eripple.util.mapper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codebros.eripple.databinding.*
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.viewholder.EmptyViewHolder
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder
import com.codebros.eripple.widget.adapter.viewholder.bookmark.SimpleErippleInfoWithBookmarkViewHolder
import com.codebros.eripple.widget.adapter.viewholder.eripple.ErippleSearchViewHolder
import com.codebros.eripple.widget.adapter.viewholder.exchange.ExchangeViewHolder
import com.codebros.eripple.widget.adapter.viewholder.history.PointSavedHistoryViewHolder
import com.codebros.eripple.widget.adapter.viewholder.notice.NoticeViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        customResourcesProvider: CustomResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)

        @Suppress("IMPLICIT_CAST_TO_ANY")
        val viewHolder = when (type) {

            CellType.EMPTY_CELL -> {
                EmptyViewHolder(
                    binding = ViewholderEmptyBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }


            CellType.BOOKMARK_CELL -> {
                SimpleErippleInfoWithBookmarkViewHolder(
                    binding = ViewholderHomeBookmarkBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }

            CellType.EVENT_CELL -> {

            }

            CellType.ERIPPLE -> {
                ErippleSearchViewHolder(
                    binding = ViewholderSearchErippleBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }

            CellType.POINT_SAVED -> {
                PointSavedHistoryViewHolder(
                    binding = ViewholderHistoryPointBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }

            CellType.EXCHANGE_HISTORY -> {
                ExchangeViewHolder(
                    binding = ViewholderExchangeHistoryBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }

            CellType.NOTICE -> {

                NoticeViewHolder(
                    binding = ViewholderNoticeBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )

            }

            else -> {
                Log.wtf("ModelViewHolderMapper", "CellType.PHOTO_CELL")
            }
        }

        return viewHolder as ModelViewHolder<M>
    }
}