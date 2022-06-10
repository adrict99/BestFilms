package com.adrict99.bestfilms.data.network.response

import com.adrict99.bestfilms.domain.model.TrendingContent
import com.google.gson.annotations.SerializedName

data class PopularAllContentResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TrendingContent>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)