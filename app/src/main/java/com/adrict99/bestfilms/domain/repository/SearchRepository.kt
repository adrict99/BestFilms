package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResults(query: String, page: Int): Flow<MultiSearchResponse>
}