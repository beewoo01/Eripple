package com.codebros.eripple.model.alarm

import android.os.Parcelable
import com.codebros.eripple.data.entity.AlarmDTO
import com.codebros.eripple.data.entity.AlarmEntity
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlarmModel(
    override val uid: Long,
    override val type: CellType = CellType.ALARM_CELL,
    val alarm_idx: Int,
    val account_account_idx: Int,
    val eripple_eripple_idx: Int,
    val alarm_content: String,
    val alarm_type: String,
    val alarm_createtime: String,
    val alarm_updatetime: String,
    val eripple_name: String

) : Model(uid, type), Parcelable {
    fun toDto() = AlarmDTO(
        alarm_idx = alarm_idx,
        account_account_idx = account_account_idx,
        eripple_eripple_idx = eripple_eripple_idx,
        alarm_content = alarm_content,
        alarm_type = alarm_type,
        alarm_createtime = alarm_createtime,
        alarm_updatetime = alarm_updatetime
    )
}