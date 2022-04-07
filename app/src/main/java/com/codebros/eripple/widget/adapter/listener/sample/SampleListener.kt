package com.codebros.eripple.widget.adapter.listener.sample

import com.codebros.eripple.model.sample.SamplePhoto
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface SampleListener : AdapterListener {
    fun onClickItem(model: SamplePhoto)
}