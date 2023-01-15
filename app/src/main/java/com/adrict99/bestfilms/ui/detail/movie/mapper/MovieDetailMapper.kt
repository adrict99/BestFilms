package com.adrict99.bestfilms.ui.detail.movie.mapper

import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.domain.model.media.movie.presentation.PresentationMovie
import com.adrict99.bestfilms.utils.extensions.*

fun MovieDetailResponse.toPresentationModel(): PresentationMovie =
    PresentationMovie(
        backdropPath = this.backdropPath,
        id = this.id,
        customOriginalLanguage = this.languages.first().name,
        productionCountry = this.productionCountries.toUnicodeFlag(),
        overview = this.overview,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage.round(1).toString(),
        runtime = this.runtime.asMinutes(),
        genres = this.genres.genreJoinToString(),
    )