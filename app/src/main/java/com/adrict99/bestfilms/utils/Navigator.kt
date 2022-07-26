package com.adrict99.bestfilms.utils

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.adrict99.bestfilms.ui.common.BaseActivity

class Navigator {

    fun navigate(context: Context, intent: Intent?, options: ActivityOptionsCompat) {
        (context as BaseActivity<*>).startActivity(intent, options.toBundle())
    }

}