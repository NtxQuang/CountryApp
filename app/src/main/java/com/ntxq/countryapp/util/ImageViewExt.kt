package com.ntxq.countryapp.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ntxq.countryapp.R

fun ImageView.loadImageUrl(
    url: String,
    onResourceReady: Runnable? = null,
    onLoadFail: Runnable? = null
) {
    val requestBuilder: RequestBuilder<Drawable> =
        Glide.with(this).asDrawable().sizeMultiplier(0.1f)
    Glide.with(this).load(url)
        .placeholder(R.drawable.bg_place_holder)
        .listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource,
                isFirstResource: Boolean
            ): Boolean {
                onResourceReady?.run()
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFail?.run()
                return false
            }
        }).thumbnail(requestBuilder)
        .into(this)
}