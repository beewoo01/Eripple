package com.codebros.eripple.model.event

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("event_idx")
    val eventIdx : Int,
    @SerializedName("event_title")
    val eventTitle : String,
    @SerializedName("event_createtime")
    val eventCreateTime : String,
    @SerializedName("event_updatetime")
    val event_updatetime : String,
    )
