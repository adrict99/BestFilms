package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.repository.MoviesRepositoryImpl
import com.adrict99.bestfilms.domain.repository.MoviesRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMoviesRepository(apiInterface: ApiInterface, networkUtils: NetworkUtils): MoviesRepository {
        return MoviesRepositoryImpl(apiInterface, networkUtils)
    }

}