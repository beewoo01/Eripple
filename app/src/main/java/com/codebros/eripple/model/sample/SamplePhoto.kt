package com.codebros.eripple.model.sample

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model


data class SamplePhoto(

    override val uid: Long,
    override val type: CellType = CellType.EMPTY_CELL,

    val albumId: Int,
    val photoId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String

) : Model(uid, type)
