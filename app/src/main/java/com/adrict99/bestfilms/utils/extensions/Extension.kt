package com.adrict99.bestfilms.utils.extensions

import android.app.Activity
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.ui.common.decorator.MarginItemDecoration
import com.adrict99.bestfilms.ui.home.mapper.addBaseImageUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun Activity?.showCustomMessage(text: String, timeExpose: Int) {
    val view: View = this!!.findViewById(android.R.id.content)
    if (text.isNotEmpty()) Snackbar.make(view, text, timeExpose).show()
}

fun ImageView.fromUrl(
    url: String?,
    placeholder: Int,
    scaleType: Transformation<Bitmap>?,
) {
    if (url?.isNotEmpty() == true && url != "null") {
        Glide.with(this)
            .load(url.addBaseImageUrl())
            .transform(RoundedCorners(30), scaleType)
            .apply(RequestOptions().placeholder(placeholder))
            .into(this)
    } else if (url?.isEmpty() == true || url == "null") {
        Glide.with(this)
            .load(placeholder)
            .apply(RequestOptions().placeholder(placeholder))
            .into(this)
    }
}

fun TextView.changeExpandableMode(expandableTextView: TextView) {
    this.setOnClickListener {
        if (expandableTextView.maxLines == 3) {
            expandableTextView.maxLines = Int.MAX_VALUE
            this.text = resources.getString(R.string.show_less)
        } else {
            expandableTextView.maxLines = 3
            this.text = resources.getString(R.string.show_more)
        }
    }
}

fun RecyclerView.setupAdapter(
    AxisMode: Int,
    verticalMode: Boolean,
    firstItemMargin: Int? = null,
) {
    layoutManager = LinearLayoutManager(context, AxisMode, false)
    setHasFixedSize(true)
    if (firstItemMargin != null) {
        addItemDecoration(MarginItemDecoration(firstItemMargin, verticalMode))
    }
}