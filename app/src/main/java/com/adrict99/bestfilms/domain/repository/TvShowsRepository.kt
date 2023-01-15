package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.detail.TvShowDetailResponse
import com.adrict99.bestfilms.data.network.response.media.TvShowsResponse
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    fun getPopularTvShows(): Flow<TvShowsResponse>
    fun getTvShowsDetail(id: Int): Flow<TvShowDetailResponse>
}