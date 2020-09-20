package com.example.cupboardigi.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cupboardigi.R

const val SHAPE_CIRCLE = "SHAPE_CIRCLE"

@BindingAdapter(value = ["imageLink", "shapeImage"], requireAll = false)
fun loadImageLink(view: ImageView, imageLink: String?, shapeImage: String?) {
    val radius = view.context.resources.getDimensionPixelSize(R.dimen.dimen_small)
    val context = view.context
    if (imageLink?.isNotEmpty() == true) {
        val glideUrl = GlideUrl(
            imageLink, LazyHeaders.Builder()
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                )
                .build()
        )
        when (shapeImage) {
            SHAPE_CIRCLE -> {
                Glide
                    .with(context)
                    .apply {
                        RequestOptions()
                            .error(R.color.colorDarkWood)
                            .placeholder(R.color.colorRustyRed)
                    }
                    .load(glideUrl)
                    .circleCrop()
                    .into(view)
            }
            else -> {
                Glide
                    .with(context)
                    .apply {
                        RequestOptions()
                            .error(R.color.colorDarkWood)
                            .placeholder(R.color.colorRustyRed)
                    }
                    .load(glideUrl)
                    .transform(RoundedCorners(radius))
                    .into(view)
            }
        }

    }
}

@BindingAdapter(value = ["imageDrawable", "shapeImage"], requireAll = false)
fun loadImageDrawable(view: ImageView, imageDrawable: String?, shapeImage: String?) {
    val radius = view.context.resources.getDimensionPixelSize(R.dimen.dimen_small)
    val context = view.context
    val src = view.context.resources.getIdentifier(imageDrawable, "drawable", view.context.packageName)
    when (shapeImage) {
        SHAPE_CIRCLE -> {
            Glide
                .with(context)
                .apply {
                    RequestOptions()
                        .error(R.color.colorDarkWood)
                        .placeholder(R.color.colorRustyRed)
                }
                .load(src)
                .circleCrop()
                .into(view)
        }
        else -> {
            Glide
                .with(context)
                .apply {
                    RequestOptions()
                        .error(R.color.colorDarkWood)
                        .placeholder(R.color.colorRustyRed)
                }
                .load(R.drawable.bg_toast)
                .transform(RoundedCorners(radius))
                .into(view)
        }
    }

}