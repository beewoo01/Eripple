package com.codebros.eripple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionEntity(

    @SerializedName("question_idx")
    val question_idx :Int,

    @SerializedName("question_title")
    val question_title : String,

    @SerializedName("question_contents")
    val question_contents : String,

    @SerializedName("question_createtime")
    val question_createtime : String,

    @SerializedName("question_updatetime")
    val question_updatetime : String

): Parcelable