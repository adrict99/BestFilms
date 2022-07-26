package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.media.PopularMoviesResponse
import com.adrict99.bestfilms.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    fun execute(): Flow<PopularMoviesResponse> = repository.getPopularMovies()
}