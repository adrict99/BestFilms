package com.adrict99.bestfilms.ui.detail.movie.mapper

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.domain.model.media.movie.presentation.PresentationActor

fun MovieCreditsResponse.toPresentationModel(): List<PresentationActor> = this.cast.map {
    PresentationActor(name = it.name, imageUrl = it.profilePath)
}