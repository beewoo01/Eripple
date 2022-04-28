package com.codebros.eripple.data.entity


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class ErippleEntity(
    @SerializedName("eripple_idx")
    @Expose
    val eripple_idx : Int,

    @SerializedName("eripple_name")
    @Expose
    val eripple_name : String,

    @SerializedName("eripple_longitude")
    @Expose
    val eripple_longitude : String,

    @SerializedName("eripple_latitude")
    @Expose
    val eripple_latitude : String,

    @SerializedName("eripple_address")
    @Expose
    val eripple_address : String,

    @SerializedName("eripple_address_detail")
    @Expose
    val eripple_address_detail : String?,

    @SerializedName("eripple_thumbnail")
    @Expose
    val eripple_thumbnail : String,

    @SerializedName("eripple_status")
    @Expose
    val eripple_status : Int,

    @SerializedName("eripple_createtime")
    @Expose
    val eripple_createtime : String,

    @SerializedName("eripple_updatetime")
    @Expose
    val eripple_updatetime : String,

    @SerializedName("bookmark_idx")
    @Expose
    val bookmark_idx : Int

): Parcelable
