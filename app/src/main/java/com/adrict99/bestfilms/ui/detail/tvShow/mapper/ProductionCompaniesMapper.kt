package com.adrict99.bestfilms.ui.detail.tvShow.mapper

import com.adrict99.bestfilms.domain.model.media.tvShow.ProductionCompany
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationProductionCompany

fun List<ProductionCompany>.toPresentationModel(): List<PresentationProductionCompany> =
    this.map {
        PresentationProductionCompany(
            imageUrl = it.logoPath,
            name = it.name,
        )
    }