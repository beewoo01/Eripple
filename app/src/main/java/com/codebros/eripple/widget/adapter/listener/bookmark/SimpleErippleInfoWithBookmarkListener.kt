package com.codebros.eripple.widget.adapter.listener.bookmark

import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface SimpleErippleInfoWithBookmarkListener : AdapterListener {
    fun onClickItem(model : SimpleErippleInfoWithBookmark)

    fun onHeartClick(model : SimpleErippleInfoWithBookmark)
}