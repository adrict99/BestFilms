package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.media.TvShowsResponse
import com.adrict99.bestfilms.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTvShowsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    fun execute(page: Int? = 1): Flow<TvShowsResponse> = repository.getPopularTvShows(page!!)
}