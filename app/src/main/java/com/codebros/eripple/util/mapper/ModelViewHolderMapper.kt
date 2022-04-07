package com.codebros.eripple.util.mapper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codebros.eripple.databinding.ViewholderEmptyBinding
import com.codebros.eripple.databinding.ViewholderSamplePhotoBinding
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.viewholder.EmptyViewHolder
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder
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
                Log.wtf("ModelViewHolderMapper", "CellType.EMPTY_CELL")
                Log.wtf("ModelViewHolderMapper", CellType.EMPTY_CELL.toString())
                Log.wtf("ModelViewHolderMapper", CellType.PHOTO_CELL.toString())
                EmptyViewHolder(
                    binding = ViewholderEmptyBinding.inflate(inflater, parent, false),
                    viewModel = viewModel,
                    customResourcesProvider = customResourcesProvider
                )
            }

            CellType.PHOTO_CELL -> {
                Log.wtf("ModelViewHolderMapper", "CellType.PHOTO_CELL")
                SampleViewHolder(
                    ViewholderSamplePhotoBinding.inflate(inflater, parent, false),
                    viewModel,
                    customResourcesProvider
                )
            }


        }

        return viewHolder as ModelViewHolder<M>
    }
}