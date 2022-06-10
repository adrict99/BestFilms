package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.data.network.response.PopularAllContentResponse
import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import com.adrict99.bestfilms.data.network.response.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

    @GET("/3/trending/all/day")
    suspend fun getPopularAll(): Response<PopularAllContentResponse>

    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(): Response<PopularTvShowsResponse>
}