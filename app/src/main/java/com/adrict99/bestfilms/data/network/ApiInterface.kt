package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.MoviePicturesResponse
import com.adrict99.bestfilms.data.network.response.detail.TvShowDetailResponse
import com.adrict99.bestfilms.data.network.response.media.AllContentResponse
import com.adrict99.bestfilms.data.network.response.media.MoviesResponse
import com.adrict99.bestfilms.data.network.response.media.TvShowsResponse
import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): Response<MovieDetailResponse>

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(@Path("movie_id") id: Int): Response<MoviePicturesResponse>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id: Int): Response<MovieCreditsResponse>

    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(@Query("page") page: Int): Response<TvShowsResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvDetail(@Path("tv_id") id: Int): Response<TvShowDetailResponse>

    @GET("/3/trending/movie/day")
    suspend fun getPopularAll(@Query("page") page: Int): Response<AllContentResponse>

    @GET("/3/search/multi")
    suspend fun getSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MultiSearchResponse>
}