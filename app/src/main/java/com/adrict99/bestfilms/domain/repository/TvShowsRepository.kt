package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.data.network.response.media.PopularTvShowsResponse
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    fun getPopularTvShows(): Flow<PopularTvShowsResponse>
    fun getTvShowsDetail(id: Int): Flow<TvDetailResponse>
}