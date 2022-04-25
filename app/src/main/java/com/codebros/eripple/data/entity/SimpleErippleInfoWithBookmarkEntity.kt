package com.codebros.eripple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleErippleInfoWithBookmarkEntity(

    @SerializedName("eripple_idx")
    @Expose
    val eripple_idx : Int,

    @SerializedName("bookmark_idx")
    @Expose
    val bookmark_idx : Int,

    @SerializedName("eripple_name")
    @Expose
    val eripple_name : String,

    @SerializedName("eripple_status")
    @Expose
    val eripple_status : Int
    
) : Parcelable
