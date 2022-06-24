package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.PopularTvShowsResponse
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    fun getPopularTvShows(): Flow<PopularTvShowsResponse>
}