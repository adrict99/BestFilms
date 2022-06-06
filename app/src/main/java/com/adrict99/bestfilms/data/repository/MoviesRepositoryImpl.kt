package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import com.adrict99.bestfilms.domain.repository.MoviesRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    override val networkUtils: NetworkUtils
): MoviesRepository, Repository(networkUtils) {

    override fun getPopularMovies(): Flow<PopularMoviesResponse> = flow {
        callApi { apiInterface.getPopularMovies() }
            .collect { popularMoviesList ->
                emit(popularMoviesList)
            }
    }

}