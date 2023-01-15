package com.adrict99.bestfilms.domain.model.media.movie.presentation

data class PresentationMovie(
    val backdropPath: String?,
    val id: Int?,
    val customOriginalLanguage: String,
    val productionCountry: String,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val voteAverage: String?,
    val runtime: String,
    val genres: String,
)