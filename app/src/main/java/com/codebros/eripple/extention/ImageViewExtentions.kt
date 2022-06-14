package com.codebros.eripple.extention

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codebros.eripple.R

private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

fun ImageView.clear() = Glide.with(context).clear(this)

@SuppressLint("CheckResult")
fun ImageView.load(
    url: String,
    corner: Float = 0f,
    scaleType: Transformation<Bitmap> = CenterInside()
) {

    val glideUrl = GlideUrl(
        url, LazyHeaders.Builder().addHeader(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
        ).build()
    )

    Glide.with(this).load(glideUrl)
        .placeholder(androidx.constraintlayout.widget.R.drawable.tooltip_frame_dark)
        .error(R.drawable.noimage)
        //.error(androidx.appcompat.R.drawable.tooltip_frame_dark)
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

@SuppressLint("CheckResult")
fun ImageView.localLoad(
    url: Drawable?
) {

    url?.let {
        Glide.with(this).load(it)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(this)

    }

}