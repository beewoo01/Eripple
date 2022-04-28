package com.codebros.eripple.widget.adapter.viewholder

import com.codebros.eripple.databinding.ViewholderEmptyBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener

class EmptyViewHolder(
    private val binding: ViewholderEmptyBinding,
    viewModel : BaseViewModel,
    customResourcesProvider : CustomResourcesProvider
) : ModelViewHolder<Model>(binding, viewModel, customResourcesProvider){

    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener?) = Unit
}