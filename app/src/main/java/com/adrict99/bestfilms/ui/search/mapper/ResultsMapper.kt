package com.adrict99.bestfilms.ui.search.mapper

import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import com.adrict99.bestfilms.domain.model.search.PresentationSearchResult

fun MultiSearchResponse.toPresentationModel(): List<PresentationSearchResult>? =
    this.results?.filter { it?.equals("movie") == true || it?.equals("tvShow") == true }?.map {
        PresentationSearchResult(
            it?.id,
            it?.posterPath,
            it?.title,
            it?.originalTitle,
            it?.mediaType
        )
    }