package com.codebros.eripple.extention

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

fun ImageView.clear() = Glide.with(context).clear(this)

@SuppressLint("CheckResult")
fun ImageView.load(
    url: String,
    corner: Float = 0f,
    scaleType: Transformation<Bitmap> = CenterInside()
) {

    Glide.with(this).load(url)
        .placeholder(androidx.constraintlayout.widget.R.drawable.notification_bg_low)
        .error(androidx.appcompat.R.drawable.tooltip_frame_dark)
        .transition(DrawableTransitionOptions.withCrossFade(factory))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply {
            if (corner > 0) {
                transform(scaleType)
                transform(RoundedCorners(corner.fromDpToPx()))
            }
        }

        .into(this)
}