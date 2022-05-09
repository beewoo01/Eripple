package com.codebros.eripple.model.question

import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.Model

data class Question(

    override val uid: Long,
    override val type: CellType = CellType.FAQ_CELL,

    val question_idx :Int,
    val question_title : String,
    val question_contents : String,
    val question_createtime : String,
    val question_updatetime : String

) : Model(uid, type)