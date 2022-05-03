package com.codebros.eripple.model.notice

import android.os.Parcelable
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notice(
    override val uid: Long,
    override val type: CellType,
    val notice_idx: Int,
    val notice_title: String,
    val notice_contents: String

) : Model(uid, type), Parcelable