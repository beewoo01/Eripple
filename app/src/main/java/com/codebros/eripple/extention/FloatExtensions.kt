package com.codebros.eripple.extention

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

fun Float.fromDpToPx(): Int{
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.dpToPx(dp: Float, context : Context) : Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
}