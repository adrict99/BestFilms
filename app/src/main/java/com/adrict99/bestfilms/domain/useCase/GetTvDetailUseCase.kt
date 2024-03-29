package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.detail.TvShowDetailResponse
import com.adrict99.bestfilms.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvDetailUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    fun execute(id: Int): Flow<TvShowDetailResponse> = repository.getTvShowsDetail(id)
}