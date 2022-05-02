package com.codebros.eripple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountExchangeHistoryEntity(

    @SerializedName("exchange_history_idx")
    @Expose
    val exchange_history_idx : Int,

    @SerializedName("exchange_cancel_idx")
    @Expose
    val exchange_cancel_idx : Int?,

    @SerializedName("exchange_history_applier_point")
    @Expose
    val exchange_history_applier_point : Int,

    @SerializedName("exchange_history_status")
    @Expose
    val exchange_history_status : Int,

    @SerializedName("exchange_cancel_couse")
    @Expose
    val exchange_cancel_couse : String?,

    @SerializedName("exchange_history_createtime")
    @Expose
    val exchange_history_createtime : String
) : Parcelable
