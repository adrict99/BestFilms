package com.adrict99.bestfilms.domain.model.media.tvShow.presentation

data class PresentationTvShow(
    val posterPath: String?,
    val name: String?,
    val backdropPath: String?,
    val id: Int?,
    val firstAirDate: String?,
    val averageEpisodeRunTime: String?,
    val originCountry: String?,
    val originalLanguage: String?,
    val episodesNumber: String,
    val seasonsNumber: String,
    val overview: String?,
)
