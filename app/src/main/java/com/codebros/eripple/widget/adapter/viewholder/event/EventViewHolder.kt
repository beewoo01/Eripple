package com.codebros.eripple.widget.adapter.viewholder.event

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import com.codebros.eripple.data.url.DefaultUrl.SAMPLE_IMAGE_URL
import com.codebros.eripple.databinding.ViewholderEventBinding
import com.codebros.eripple.extention.clear
import com.codebros.eripple.extention.load
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.screen.base.BaseViewModel
import com.codebros.eripple.util.provider.CustomResourcesProvider
import com.codebros.eripple.widget.adapter.listener.AdapterListener
import com.codebros.eripple.widget.adapter.listener.event.EventWithThumbnailListener
import com.codebros.eripple.widget.adapter.viewholder.ModelViewHolder
import java.text.SimpleDateFormat
import java.util.*

class EventViewHolder(
    private val binding: ViewholderEventBinding,
    viewModel: BaseViewModel,
    customResourcesProvider: CustomResourcesProvider,
) : ModelViewHolder<EventWithThumbnail>(binding, viewModel, customResourcesProvider) {

    override fun reset() = with(binding){
        eventImgImv.clear()
        titleTxv.text = ""
    }

    override fun bindViews(model: EventWithThumbnail, adapterListener: AdapterListener?) =
        with(binding) {
            root.setOnClickListener {

                if (adapterListener is EventWithThumbnailListener) {
                    adapterListener.onClickItem(model)
                }

            }
        }

    @SuppressLint("SimpleDateFormat")
    override fun bindData(model: EventWithThumbnail) = with(binding) {
        super.bindData(model)

        titleTxv.text = model.event_title
        eventImgImv.load(SAMPLE_IMAGE_URL + model.event_image_url, 20F)
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val parse = sdf.parse(model.event_updateTime)
        if (parse != null) {
            val date1 = cal.get(Calendar.DAY_OF_MONTH)
            cal.time = parse
            val date2 = cal.get(Calendar.DAY_OF_MONTH)
            val between = date1.compareTo(date2)
            newTxv.isVisible = between <= 7
        }

    }

}
