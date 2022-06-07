package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): MainActivity
}