package com.codebros.eripple.model.event

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model

data class SimpleErippleInfoWithBookmark(
    override val uid: Long,
    override val type: CellType = CellType.BOOKMARK_CELL,
    val eripple_idx : Int,
    val bookmark_idx : Int,
    val eripple_name : String,
    val eripple_status : Int

) : Model(uid, type)
