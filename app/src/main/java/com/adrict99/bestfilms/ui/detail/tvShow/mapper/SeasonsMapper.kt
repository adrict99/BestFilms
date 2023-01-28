package com.adrict99.bestfilms.ui.detail.tvShow.mapper

import com.adrict99.bestfilms.domain.model.media.tvShow.Season
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationSeason

fun List<Season>.toPresentationModel(): List<PresentationSeason> =
    this.map {
        PresentationSeason(
            imageUrl = it.posterPath,
            name = it.name,
        )
    }