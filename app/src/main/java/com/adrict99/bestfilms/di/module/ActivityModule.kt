package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity
}