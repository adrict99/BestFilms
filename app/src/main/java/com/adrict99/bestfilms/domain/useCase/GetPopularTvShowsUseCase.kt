package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.media.PopularTvShowsResponse
import com.adrict99.bestfilms.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTvShowsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    fun execute(): Flow<PopularTvShowsResponse> = repository.getPopularTvShows()
}