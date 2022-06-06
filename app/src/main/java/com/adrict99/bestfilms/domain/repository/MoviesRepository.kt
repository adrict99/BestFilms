package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<PopularMoviesResponse>
}