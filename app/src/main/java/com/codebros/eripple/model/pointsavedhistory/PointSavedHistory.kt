package com.codebros.eripple.model.pointsavedhistory

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model

data class PointSavedHistory(

    override val uid: Long,
    override val type: CellType = CellType.POINT_SAVED_CELL,
    val waste_discharge_record_idx: Int,
    val eripple_idx: Int,
    val eripple_name: String,
    val eripple_address: String,
    val eripple_address_detail: String?,
    val waste_discharge_record_gram: Float,
    val waste_discharge_record_earned_points: Int,
    val waste_discharge_record_updatetime: String,
    var viewStatus : Boolean

) : Model(uid, type)
