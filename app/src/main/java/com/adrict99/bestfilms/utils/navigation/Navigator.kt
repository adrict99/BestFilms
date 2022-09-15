package com.adrict99.bestfilms.utils.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.adrict99.bestfilms.ui.MainActivity
import com.adrict99.bestfilms.ui.common.BaseActivity
import com.adrict99.bestfilms.ui.home.HomeFragmentDirections
import com.adrict99.bestfilms.utils.types.MediaType

class Navigator {

    fun navigate(context: Context, intent: Intent?, options: ActivityOptionsCompat) {
        (context as BaseActivity<*>).startActivity(intent, options.toBundle())
    }

    private fun navigate(context: Context, intent: Intent?) {
        (context as Activity).startActivity(intent)
    }

    fun goToMain(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        navigate(context, intent)
    }

    fun goToDetailedMedia(
        id: Int,
        type: MediaType,
        originFragment: Fragment,
    ) {
        val actionFromHomeToDetail = HomeFragmentDirections.actionHomeFragmentToDetailedMediaFragment(
            mediaId = id,
            mediaType = type,
        )
        originFragment.findNavController().navigate(actionFromHomeToDetail)
    }

}