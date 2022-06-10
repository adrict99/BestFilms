package com.adrict99.bestfilms.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.PopularAllContentResponse
import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import com.adrict99.bestfilms.data.network.response.PopularTvShowsResponse
import com.adrict99.bestfilms.domain.useCase.GetPopularAllContentUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularMoviesUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularTvShowsUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getPopularAllContentUseCase: GetPopularAllContentUseCase
): BaseViewModel() {

    //Observing data that came from the API requests
    val popularMoviesResponse: MutableLiveData<PopularMoviesResponse> by lazy { MutableLiveData<PopularMoviesResponse>() }
    val popularTvShowsResponse: MutableLiveData<PopularTvShowsResponse> by lazy { MutableLiveData<PopularTvShowsResponse>() }
    val popularAllContentResponse: MutableLiveData<PopularAllContentResponse> by lazy { MutableLiveData<PopularAllContentResponse>() }

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.execute()
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { list ->
                    popularMoviesResponse.value = list
                }
        }
    }

    fun getPopularSeries() {
        viewModelScope.launch {
            getPopularTvShowsUseCase.execute()
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { list ->
                    popularTvShowsResponse.value = list
                }
        }
    }

    fun getPopularAllContent() {
        viewModelScope.launch {
            getPopularAllContentUseCase.execute()
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { list ->
                    popularAllContentResponse.value = list
                }
        }
    }

}