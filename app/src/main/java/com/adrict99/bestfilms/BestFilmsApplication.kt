package com.adrict99.bestfilms

import android.app.Application
import com.adrict99.bestfilms.di.component.ApplicationComponent
import com.adrict99.bestfilms.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BestFilmsApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}