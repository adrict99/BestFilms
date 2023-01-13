package com.adrict99.bestfilms.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun Activity?.showCustomMessage(text: String, timeExpose: Int) {
    val view: View = this!!.findViewById(android.R.id.content)
    if (text.isNotEmpty()) {
        Snackbar.make(view, text , timeExpose).show()
    }
}

fun ImageView.fromUrl(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this)
            .load("${BuildConfig.IMAGE_BASE_URL}$url")
            .centerCrop()
            .placeholder(R.drawable.ic_movie)
            .into(this)
    }
}