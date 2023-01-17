package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import com.adrict99.bestfilms.domain.repository.SearchRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    override val networkUtils: NetworkUtils
): SearchRepository, Repository(networkUtils) {

    override fun getSearchResults(query: String, page: Int): Flow<MultiSearchResponse> = flow {
        callApi { apiInterface.getSearchResults(query, page) }
            .collect { emit(it) }
    }

}