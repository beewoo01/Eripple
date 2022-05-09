package com.codebros.eripple.widget.adapter.viewholder.question

import androidx.core.view.isVisible
import com.codebros.eripple.databinding.ViewholderFaqBinding
import com.codebros.eripple.model.question.Question
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class FAQViewHolder(
    private val binding: ViewholderFaqBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<Question>(binding, viewModel, customResourcesProvider) {

    override fun reset() = with(binding){

        contentGroup.isVisible = false

    }

    override fun bindViews(model: Question, adapterListener: AdapterListener?) = with(binding) {

        moreImb.setOnClickListener {
            contentGroup.isVisible = !contentGroup.isVisible
        }

    }

    override fun bindData(model: Question) = with(binding) {
        super.bindData(model)

        titleTxv.text = model.question_title
        contentTxv.text = model.question_contents


    }
}