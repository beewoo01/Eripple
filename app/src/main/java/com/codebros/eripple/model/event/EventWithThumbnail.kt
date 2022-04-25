package com.codebros.eripple.model.event

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model


data class EventWithThumbnail(

    override val uid: Long,
    override val type: CellType = CellType.EVENT_CELL,
    val event_idx: Int,
    val event_title: String,
    val event_createTime: String,
    val event_updateTime: String,
    val event_image_idx: Int,
    val event_image_url: String

) : Model(uid, type)
