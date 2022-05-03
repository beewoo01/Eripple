package com.codebros.eripple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoticeEntity(

    @SerializedName("notice_idx")
    val notice_idx: Int,

    @SerializedName("notice_title")
    val notice_title: String,

    @SerializedName("notice_contents")
    val notice_contents: String

) : Parcelable