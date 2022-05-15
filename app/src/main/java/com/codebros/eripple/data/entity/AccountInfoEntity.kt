package com.codebros.eripple.data.entity

import com.google.gson.annotations.SerializedName

data class AccountInfoEntity(
    @SerializedName("account_email")
    val account_email: String,
    @SerializedName("account_name")
    val account_name: String,
    @SerializedName("account_phone")
    val account_phone: String
)