package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.MoviePicturesResponse
import com.adrict99.bestfilms.data.network.response.media.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(page: Int): Flow<MoviesResponse>
    fun getMovieDetail(id: Int): Flow<MovieDetailResponse>
    fun getMoviePictures(id: Int): Flow<MoviePicturesResponse>
    fun getMovieCredits(id: Int): Flow<MovieCreditsResponse>
}