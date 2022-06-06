package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.ui.HomeActivity
import dagger.android.ContributesAndroidInjector

abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity
}