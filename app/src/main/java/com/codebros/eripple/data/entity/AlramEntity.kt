package com.codebros.eripple.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlarmEntity(
    @SerializedName("alarm_idx")
    val alarm_idx : Int,
    @SerializedName("account_account_idx")
    val account_account_idx : Int,
    @SerializedName("eripple_eripple_idx")
    val eripple_eripple_idx : Int,
    @SerializedName("alarm_content")
    val alarm_content : String,
    @SerializedName("alarm_type")
    val alarm_type : String,
    @SerializedName("alarm_createtime")
    val alarm_createtime : String,
    @SerializedName("alarm_updatetime")
    val alarm_updatetime : String,
    @SerializedName("eripple_name")
    val eripple_name: String
)

data class AlarmDTO(
    @Expose
    val alarm_idx : Int,
    @Expose
    val account_account_idx : Int,
    @Expose
    val eripple_eripple_idx : Int,
    @Expose
    val alarm_content : String,
    @Expose
    val alarm_type : String,
    @Expose
    val alarm_createtime : String,
    @Expose
    val alarm_updatetime : String,
)