package com.adrict99.bestfilms.domain.repository

import com.adrict99.bestfilms.data.network.response.PopularAllContentResponse
import kotlinx.coroutines.flow.Flow

interface PopularAllContentRepository {
    fun getPopularAllContent(): Flow<PopularAllContentResponse>
}