package com.adrict99.bestfilms.ui.detail.movie.mapper

import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.domain.model.detail.Actor
import com.adrict99.bestfilms.domain.model.detail.Cast
import com.adrict99.bestfilms.domain.model.detail.Crew

fun MovieCreditsResponse.toPresentationModel(): List<Actor> = this.cast.map {
    Actor(name = it.name, imageUrl = it.profilePath)
}