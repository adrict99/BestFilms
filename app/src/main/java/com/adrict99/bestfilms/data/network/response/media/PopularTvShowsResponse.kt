package com.adrict99.bestfilms.data.network.response.media

import com.adrict99.bestfilms.domain.model.media.TvShow
import com.google.gson.annotations.SerializedName

data class PopularTvShowsResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TvShow>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)