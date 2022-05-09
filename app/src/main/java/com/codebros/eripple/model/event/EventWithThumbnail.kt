package com.codebros.eripple.model.event

import android.os.Parcelable
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import kotlinx.parcelize.Parcelize


@Parcelize
data class EventWithThumbnail(

    override val uid: Long,
    override val type: CellType = CellType.HOME_EVENT_CELL,
    val event_idx: Int,
    val event_title: String,
    val event_createTime: String,
    val event_updateTime: String,
    val event_image_idx: Int,
    val event_image_url: String

) : Model(uid, type), Parcelable
