package com.adrict99.bestfilms.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adrict99.bestfilms.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter(
    "loadFromUrl",
    "roundedCorners",
    "circleCrop",
    "placeholder",
    requireAll = false
)
fun loadImage(
    view: ImageView,
    imageUrl: String?,
    roundedCorners: Boolean,
    circleCrop: Boolean,
    placeholder: Drawable?,
) {
    //TODO: Fix this with a mapper Domain -> Presentation
    val url = BuildConfig.IMAGE_BASE_URL + imageUrl
    Glide.with(view.context).load(url).let { requestBuilder ->
        if (roundedCorners) requestBuilder.transform(RoundedCorners(20))
        if (circleCrop) requestBuilder.circleCrop()
        if (placeholder != null) requestBuilder.placeholder(placeholder)
        requestBuilder.centerCrop().into(view)
    }
}