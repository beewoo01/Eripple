package com.codebros.eripple.data.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class EventWithThumbnailEntity(
    val event_idx : Int,
    val event_title : String,
    val event_createtime : String,
    val event_updatetime : String,
    val event_image_idx : Int,
    val event_image_url : String,
) : Parcelable
