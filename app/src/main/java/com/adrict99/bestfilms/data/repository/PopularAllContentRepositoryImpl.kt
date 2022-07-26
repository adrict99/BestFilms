package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.data.network.ApiInterface
import com.adrict99.bestfilms.data.network.response.media.PopularAllContentResponse
import com.adrict99.bestfilms.data.repository.Repository
import com.adrict99.bestfilms.domain.repository.PopularAllContentRepository
import com.adrict99.bestfilms.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularAllContentRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    override val networkUtils: NetworkUtils
): PopularAllContentRepository, Repository(networkUtils) {

    override fun getPopularAllContent(): Flow<PopularAllContentResponse> = flow {
        callApi { apiInterface.getPopularAll() }
            .collect { popularAll ->
                emit(popularAll)
            }
    }

}