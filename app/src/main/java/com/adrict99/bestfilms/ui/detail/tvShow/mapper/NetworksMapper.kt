package com.adrict99.bestfilms.ui.detail.tvShow.mapper

import com.adrict99.bestfilms.domain.model.media.tvShow.Network
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationNetwork

fun List<Network>.toPresentationModel(): List<PresentationNetwork> =
    this.map {
        PresentationNetwork(
            imageUrl = it.logoPath,
            name = it.name,
        )
    }