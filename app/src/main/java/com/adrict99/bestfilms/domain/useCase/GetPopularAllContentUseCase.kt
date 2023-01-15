package com.adrict99.bestfilms.domain.useCase

import com.adrict99.bestfilms.data.network.response.media.AllContentResponse
import com.adrict99.bestfilms.domain.repository.PopularAllContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularAllContentUseCase @Inject constructor(
    private val repository: PopularAllContentRepository
) {
    fun execute(): Flow<AllContentResponse> = repository.getPopularAllContent()
}