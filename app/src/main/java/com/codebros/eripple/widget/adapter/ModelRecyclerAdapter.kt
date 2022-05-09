package com.codebros.eripple.widget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.mapper.ModelViewHolderMapper
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class ModelRecyclerAdapter<M : Model, VM : BaseViewModel>(
    private var modelList: List<Model>,
    private var viewModel: VM,
    private val customResourcesProvider: CustomResourcesProvider,
    private val adapterListener: AdapterListener?

) : ListAdapter<Model, ModelViewHolder<M>>(Model.DIFF_CALLBACK) {

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun getItemViewType(position: Int): Int = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(
            parent,
            CellType.values()[viewType],
            viewModel,
            customResourcesProvider
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        with(holder) {
            bindData(modelList[position] as M)
            bindViews(modelList[position] as M, adapterListener)
        }
    }

    override fun submitList(list: MutableList<Model>?) {
        list?.let { modelList = it }
        super.submitList(list)
    }

}