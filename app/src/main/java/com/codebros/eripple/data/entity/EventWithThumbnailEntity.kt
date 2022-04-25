package com.codebros.eripple.data.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class EventWithThumbnailEntity(
    @SerializedName("event_idx")
    @Expose
    val event_idx: Int,

    @SerializedName("event_title")
    @Expose
    val event_title: String,

    @SerializedName("event_createtime")
    @Expose
    val event_createtime: String,

    @SerializedName("event_updatetime")
    @Expose
    val event_updatetime: String,

    @SerializedName("event_image_idx")
    @Expose
    val event_image_idx: Int,

    @SerializedName("event_image_url")
    @Expose
    val event_image_url: String,
) : Parcelable
