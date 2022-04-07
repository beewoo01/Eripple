package com.codebros.eripple.widget.adapter.viewholder.sample

import android.util.Log
import com.codebros.eripple.databinding.ViewholderSamplePhotoBinding
import com.codebros.eripple.model.sample.SamplePhoto
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.sample.SampleListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder

class SampleViewHolder(
    private val binding : ViewholderSamplePhotoBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider
) : ModelViewHolder<SamplePhoto>(binding, viewModel, customResourcesProvider) {

    override fun reset() = with(binding){
        Log.wtf("SampleViewHolder", "reset")
        thumbnailImage.clear()
    }

    override fun bindData(model: SamplePhoto) {
        super.bindData(model)
        Log.wtf("SampleViewHolder", "bindData")
        with(binding) {
            Log.wtf("SampleViewHolder", "bindData with(binding)")
            thumbnailImage.load(model.thumbnailUrl + ".png", 24f)
            titleTxv.text = model.title
        }
    }

    override fun bindViews(model: SamplePhoto, adapterListener: AdapterListener) {
        Log.wtf("SampleViewHolder", "bindViews")
        with(binding) {
            Log.wtf("SampleViewHolder", "bindViews with(binding)")
            if (adapterListener is SampleListener) {
                root.setOnClickListener {
                    adapterListener.onClickItem(model)
                }
            }
        }
    }
}