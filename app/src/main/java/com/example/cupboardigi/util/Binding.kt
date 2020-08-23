package com.example.cupboardigi.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cupboardigi.R

@BindingAdapter("imageResource")
fun loadImage(view: ImageView, imageUrl: String?) {
    val radius = view.context.resources.getDimensionPixelSize(R.dimen.dimen_small)
    val context = view.context
    if (imageUrl?.isNotEmpty() == true) {
        val glideUrl = GlideUrl(
            imageUrl, LazyHeaders.Builder()
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                )
                .build()
        )
        Glide.with(context).load(glideUrl).transform(RoundedCorners(radius)).into(view)
    }
}