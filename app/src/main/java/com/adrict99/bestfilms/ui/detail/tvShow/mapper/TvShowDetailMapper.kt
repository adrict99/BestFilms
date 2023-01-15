package com.adrict99.bestfilms.ui.detail.tvShow.mapper

import com.adrict99.bestfilms.data.network.response.detail.TvShowDetailResponse
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationTvShow
import com.adrict99.bestfilms.utils.extensions.asMinutes
import com.adrict99.bestfilms.utils.extensions.calculateAverage
import com.adrict99.bestfilms.utils.extensions.toOriginalLanguage
import com.adrict99.bestfilms.utils.extensions.toUnicodeFlag

fun TvShowDetailResponse.toPresentationModel(): PresentationTvShow =
    PresentationTvShow(
        posterPath = this.posterPath,
        name = this.name,
        backdropPath = this.backdropPath,
        id = this.id,
        firstAirDate = this.firstAirDate,
        averageEpisodeRunTime = this.episodeRunTime.calculateAverage().asMinutes(),
        originCountry = this.originCountry.toUnicodeFlag(),
        originalLanguage = this.spokenLanguages.toOriginalLanguage(),
        episodesNumber = this.numberOfEpisodes.toString(),
        seasonsNumber = this.numberOfSeasons.toString(),
        overview = this.overview,
    )