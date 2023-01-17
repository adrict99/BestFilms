package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import com.adrict99.bestfilms.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    fun execute(query: String, page: Int): Flow<MultiSearchResponse> =
        repository.getSearchResults(query, page)
}