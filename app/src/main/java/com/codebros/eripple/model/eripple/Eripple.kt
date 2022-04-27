package com.codebros.eripple.model.eripple

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model

data class Eripple(
    override val uid: Long,
    override val type: CellType = CellType.ERIPPLE,
    val eripple_idx : Int,
    val eripple_name : String,
    val eripple_longitude : String,
    val eripple_latitude : String,
    val eripple_address : String,
    val eripple_address_detail : String?,
    val eripple_thumbnail : String,
    val eripple_status : Int,
    val eripple_createtime : String,
    val eripple_updatetime : String,
    var bookmark_idx : Int

) : Model(uid, type)