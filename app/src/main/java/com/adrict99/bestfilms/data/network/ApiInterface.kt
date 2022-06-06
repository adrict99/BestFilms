package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>
}