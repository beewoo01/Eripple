package com.codebros.eripple.model.bank

import android.os.Parcelable
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    override val uid: Long,
    override val type: CellType = CellType.BANK,
    val bank_idx: Int,
    val bank_name: String,
    val bank_img: String
) : Model(uid, type), Parcelable