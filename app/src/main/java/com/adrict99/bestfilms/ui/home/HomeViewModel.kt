package com.adrict99.bestfilms.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.media.ContentResponse
import com.adrict99.bestfilms.domain.useCase.GetPopularAllContentUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularMoviesUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularTvShowsUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getPopularAllContentUseCase: GetPopularAllContentUseCase
) : BaseViewModel() {

    //Storing responses from the API requests
    val contentResponse: MutableLiveData<ContentResponse> by lazy { MutableLiveData<ContentResponse>() }

    //TODO: Infinite scrolling implementation
    var currentMoviesPage: Int = 1
    var currentTvShowsPage: Int = 1
    var currentPopularContentPage: Int = 1

    /*async creates a new coroutine, await suspends current coroutine and waits for the result,
        first return the network request responses emitted by the flow*/
    fun getHomeData() {
        viewModelScope.launch {
            val job1 = async { getPopularMoviesUseCase.execute().first() }
            val job2 = async { getPopularTvShowsUseCase.execute().first() }
            val job3 = async { getPopularAllContentUseCase.execute().first() }
            try {
                val response = ContentResponse(job1.await(), job2.await(), job3.await())
                contentResponse.value = response
            } catch (e: Exception) {
                errorMessage.value = mapOf(0 to (e.message ?: ""))
            }
        }
    }

    fun getMoviesData() {
        viewModelScope.launch {
            loading.value = SHOW
            getPopularMoviesUseCase.execute(currentMoviesPage)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect {
                    contentResponse.value?.moviesResponse = it
                }
            loading.value = DISMISS
        }
    }

    fun getTvShowsData() {
        viewModelScope.launch {
            loading.value = SHOW
            getPopularTvShowsUseCase.execute(currentTvShowsPage)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect {
                    contentResponse.value?.tvShowsResponse = it
                }
            loading.value = DISMISS
        }
    }

    fun getPopularContentData() {
        viewModelScope.launch {
            loading.value = SHOW
            getPopularAllContentUseCase.execute(currentPopularContentPage)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect {
                    contentResponse.value?.allContentResponse = it
                }
            loading.value = DISMISS
        }
    }

}