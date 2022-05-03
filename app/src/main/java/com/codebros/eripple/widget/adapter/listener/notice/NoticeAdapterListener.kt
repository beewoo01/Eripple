package com.codebros.eripple.widget.adapter.listener.notice

import com.codebros.eripple.model.notice.Notice
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface NoticeAdapterListener : AdapterListener {
    fun onItemClick(model : Notice)
}