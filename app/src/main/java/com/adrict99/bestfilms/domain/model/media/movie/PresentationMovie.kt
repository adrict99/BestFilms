package com.adrict99.bestfilms.domain.model.media.movie

data class PresentationMovie(
    val backdropPath: String?,
    val id: Int?,
    val originalLanguage: String?,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val runtime: String,
    val genres: String,
)