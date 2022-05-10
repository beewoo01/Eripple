package com.codebros.eripple.data.entity

import com.google.gson.annotations.SerializedName

data class BankEntity(
    @SerializedName("bank_idx")
    val bank_idx : Int,
    @SerializedName("bank_name")
    val bank_name : String,
    @SerializedName("bank_img")
    val bank_img : String
)