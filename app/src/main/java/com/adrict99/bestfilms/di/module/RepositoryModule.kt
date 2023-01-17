package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.repository.MoviesRepositoryImpl
import com.adrict99.bestfilms.data.repository.PopularAllContentRepositoryImpl
import com.adrict99.bestfilms.data.repository.SearchRepositoryImpl
import com.adrict99.bestfilms.data.repository.TvShowsRepositoryImpl
import com.adrict99.bestfilms.domain.repository.MoviesRepository
import com.adrict99.bestfilms.domain.repository.PopularAllContentRepository
import com.adrict99.bestfilms.domain.repository.SearchRepository
import com.adrict99.bestfilms.domain.repository.TvShowsRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMoviesRepository(apiInterface: ApiInterface, networkUtils: NetworkUtils): MoviesRepository {
        return MoviesRepositoryImpl(apiInterface, networkUtils)
    }

    @Provides
    fun providesTvShowsRepository(apiInterface: ApiInterface, networkUtils: NetworkUtils): TvShowsRepository {
        return TvShowsRepositoryImpl(apiInterface, networkUtils)
    }

    @Provides
    fun providesAllContentRepository(apiInterface: ApiInterface, networkUtils: NetworkUtils): PopularAllContentRepository {
        return PopularAllContentRepositoryImpl(apiInterface, networkUtils)
    }

    @Provides
    fun providesSearchRepository(apiInterface: ApiInterface, networkUtils: NetworkUtils): SearchRepository {
        return SearchRepositoryImpl(apiInterface, networkUtils)
    }

}