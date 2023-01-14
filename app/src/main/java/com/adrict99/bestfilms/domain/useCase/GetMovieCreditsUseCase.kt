package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    fun execute(id: Int): Flow<MovieCreditsResponse> = repository.getMovieCredits(id)
}