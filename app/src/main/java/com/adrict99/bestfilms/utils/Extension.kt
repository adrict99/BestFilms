package com.adrict99.bestfilms.utils

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity?.showCustomMessage(text: String, timeExpose: Int) {
    val view: View = this!!.findViewById(android.R.id.content)
    if (text.isNotEmpty()) {
        Snackbar.make(view, text , timeExpose)
    }
}