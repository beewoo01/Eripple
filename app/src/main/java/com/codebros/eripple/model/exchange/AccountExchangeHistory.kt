package com.codebros.eripple.model.exchange

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model

data class AccountExchangeHistory(
    override val uid: Long,
    override val type: CellType = CellType.EXCHANGE_HISTORY_CELL,
    val exchange_history_idx : Int,
    val exchange_cancel_idx : Int?,
    val exchange_history_applier_point : Int,
    val exchange_history_status : Int,
    val exchange_cancel_couse : String?,
    val exchange_history_createtime : String

) : Model(uid, type)
