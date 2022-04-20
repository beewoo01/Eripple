package com.codebros.eripple.model.event

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.google.gson.annotations.SerializedName

data class EventImage(
    @SerializedName("event_image_idx")
    val eventImageIdx : Int,
    @SerializedName("event_event_idx")
    val eventIdx : Int,
    @SerializedName("event_image_url")
    val eventImageUrl : String,
    @SerializedName("event_image_isthumbnail")
    val eventImageIsThumbnail : Int,
    )