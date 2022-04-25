package com.codebros.eripple.widget.adapter.listener.event

import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface EventWithThumbnailListener: AdapterListener {
    fun onClickItem(model : EventWithThumbnail)
}