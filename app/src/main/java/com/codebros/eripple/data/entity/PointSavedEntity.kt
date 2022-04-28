package com.codebros.eripple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointSavedEntity(

    @SerializedName("waste_discharge_record_idx")
    @Expose
    val waste_discharge_record_idx : Int,

    @SerializedName("eripple_idx")
    @Expose
    val eripple_idx : Int,

    @SerializedName("eripple_name")
    @Expose
    val eripple_name : String,

    @SerializedName("eripple_address")
    @Expose
    val eripple_address : String,

    @SerializedName("eripple_address_detail")
    @Expose
    val eripple_address_detail : String,

    @SerializedName("waste_discharge_record_gram")
    @Expose
    val waste_discharge_record_gram : Float,

    @SerializedName("waste_discharge_record_earned_points")
    @Expose
    val waste_discharge_record_earned_points : Int,

    @SerializedName("waste_discharge_record_updatetime")
    @Expose
    val waste_discharge_record_updatetime : String

): Parcelable
