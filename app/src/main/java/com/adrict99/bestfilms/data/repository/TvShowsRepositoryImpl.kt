package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.network.response.PopularTvShowsResponse
import com.adrict99.bestfilms.domain.repository.TvShowsRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    override val networkUtils: NetworkUtils
): TvShowsRepository, Repository(networkUtils) {

    override fun getPopularTvShows(): Flow<PopularTvShowsResponse> = flow {
        callApi { apiInterface.getPopularTvShows() }
            .collect { popularTvShows ->
                emit(popularTvShows)
            }
    }

}