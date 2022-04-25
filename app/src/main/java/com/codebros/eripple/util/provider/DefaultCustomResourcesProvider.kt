package com.codebros.eripple.util.provider

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class DefaultCustomResourcesProvider(
    private val context: Context
) : CustomResourcesProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getString(@StringRes resId: Int, vararg formArgs: Any): String =
        context.getString(resId, *formArgs)

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getColorStateList(@ColorRes resId: Int): ColorStateList =
        context.getColorStateList(resId)

    override fun getDrawable(@DrawableRes drawable: Int): Drawable? =
        ContextCompat.getDrawable(context, drawable)
}