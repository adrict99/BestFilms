package com.adrict99.bestfilms.data.network.response.search

import com.adrict99.bestfilms.domain.model.search.SearchResult
import com.google.gson.annotations.SerializedName

data class MultiSearchResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<SearchResult?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)