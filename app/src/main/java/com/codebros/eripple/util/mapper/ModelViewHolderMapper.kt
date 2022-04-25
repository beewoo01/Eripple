package com.codebros.eripple.util.mapper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codebros.eripple.databinding.ViewholderEmptyBinding
import com.codebros.eripple.databinding.ViewholderHomeBookmarkBinding
import com.codebros.eripple.databinding.ViewholderSamplePhotoBinding
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.viewholder.EmptyViewHolder
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder
import com.codebros.eripple.widget.adapter.viewholder.bookmark.SimpleErippleInfoWithBookmarkViewHolder
import com.codebros.eripple.widget.adapter.viewholder.sample.SampleViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        customResourcesProvider: CustomResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
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

            else -> {
                Log.wtf("ModelViewHolderMapper", "CellType.PHOTO_CELL")
            }
        }

        return viewHolder as ModelViewHolder<M>
    }
}