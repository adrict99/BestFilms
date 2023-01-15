package com.adrict99.bestfilms.ui.detail.tvShow.mapper

import com.adrict99.bestfilms.domain.model.media.tvShow.Season
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationSeason
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationTvShow
import com.adrict99.bestfilms.utils.extensions.asMinutes
import com.adrict99.bestfilms.utils.extensions.calculateAverage
import com.adrict99.bestfilms.utils.extensions.toOriginalLanguage
import com.adrict99.bestfilms.utils.extensions.toUnicodeFlag

fun List<Season>.toPresentationModel(): List<PresentationSeason> =
    this.map {
        PresentationSeason(
            imageUrl = it.posterPath,
            name = it.name,
        )
    }