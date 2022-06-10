package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.PopularAllContentResponse
import com.adrict99.bestfilms.domain.repository.PopularAllContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularAllContentUseCase @Inject constructor(
    private val repository: PopularAllContentRepository
) {
    fun execute(): Flow<PopularAllContentResponse> = repository.getPopularAllContent()
}