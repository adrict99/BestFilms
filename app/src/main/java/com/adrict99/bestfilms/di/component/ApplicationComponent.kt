package com.adrict99.bestfilms.di.component

import android.app.Application
import com.adrict99.bestfilms.BestFilmsApplication
import com.adrict99.bestfilms.di.module.ActivityModule
import com.adrict99.bestfilms.di.module.ApplicationModule
import com.adrict99.bestfilms.di.module.NetworkModule
import com.adrict99.bestfilms.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    ActivityModule::class,
    RepositoryModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: BestFilmsApplication)

}