package com.adrict99.bestfilms.di.module

import android.app.Application
import android.content.Context
import com.adrict99.bestfilms.utils.NetworkUtils
import dagger.Provides
import javax.inject.Singleton

class ApplicationModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesNetWorkUtilities(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

}