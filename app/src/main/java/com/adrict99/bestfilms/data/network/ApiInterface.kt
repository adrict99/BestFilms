package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.MoviePicturesResponse
import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.data.network.response.media.PopularAllContentResponse
import com.adrict99.bestfilms.data.network.response.media.PopularMoviesResponse
import com.adrict99.bestfilms.data.network.response.media.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.google.gson.annotations.SerializedName


interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

    @GET("/3/trending/all/day")
    suspend fun getPopularAll(): Response<PopularAllContentResponse>

    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(): Response<PopularTvShowsResponse>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): Response<MovieDetailResponse>

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(@Path("movie_id") id: Int): Response<MoviePicturesResponse>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id: Int): Response<MovieCreditsResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvDetail(@Path("tv_id") id: Int): Response<TvDetailResponse>
}