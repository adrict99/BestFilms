package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.data.network.response.media.PopularAllContentResponse
import com.adrict99.bestfilms.data.network.response.media.PopularMoviesResponse
import com.adrict99.bestfilms.data.network.response.media.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

    @GET("/3/trending/all/day")
    suspend fun getPopularAll(): Response<PopularAllContentResponse>

    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(): Response<PopularTvShowsResponse>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(id: Int): Response<MovieDetailResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvDetail(id: Int): Response<TvDetailResponse>
}