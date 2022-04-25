package com.codebros.eripple.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleErippleInfoWithBookmarkEntity(
    val eripple_idx : Int,
    val bookmark_idx : Int,
    val eripple_name : String,
    val eripple_status : Int
) : Parcelable
