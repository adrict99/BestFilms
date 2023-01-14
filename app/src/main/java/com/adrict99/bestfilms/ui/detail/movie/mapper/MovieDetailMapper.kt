package com.adrict99.bestfilms.ui.detail.movie.mapper

import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.domain.model.media.movie.PresentationMovie
import com.adrict99.bestfilms.utils.extensions.asMinutes
import com.adrict99.bestfilms.utils.extensions.round
import com.adrict99.bestfilms.utils.extensions.genresListJoinToString
import com.adrict99.bestfilms.utils.extensions.toNationalFlagUnicode

fun MovieDetailResponse.toPresentationModel(): PresentationMovie =
    PresentationMovie(
        backdropPath = this.backdropPath,
        id = this.id,
        originalLanguage = this.originalLanguage.toNationalFlagUnicode(),
        overview = this.overview,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage.round(1),
        runtime = this.runtime.asMinutes(),
        genres = this.genres.genresListJoinToString(),
    )