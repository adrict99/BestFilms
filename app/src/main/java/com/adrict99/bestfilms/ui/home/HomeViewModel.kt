package com.adrict99.bestfilms.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.media.AllContentResponse
import com.adrict99.bestfilms.data.network.response.media.MoviesResponse
import com.adrict99.bestfilms.data.network.response.media.TvShowsResponse
import com.adrict99.bestfilms.domain.useCase.GetPopularAllContentUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularMoviesUseCase
import com.adrict99.bestfilms.domain.useCase.GetPopularTvShowsUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getPopularAllContentUseCase: GetPopularAllContentUseCase
): BaseViewModel() {

    //Observing data that came from the API requests
    val moviesResponse: MutableLiveData<MoviesResponse> by lazy { MutableLiveData<MoviesResponse>() }
    val tvShowsResponse: MutableLiveData<TvShowsResponse> by lazy { MutableLiveData<TvShowsResponse>() }
    val allContentResponse: MutableLiveData<AllContentResponse> by lazy { MutableLiveData<AllContentResponse>() }

    fun getPopularMovies() {
        loading.value = SHOW
        viewModelScope.launch {
            getPopularMoviesUseCase.execute()
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { list ->
                    moviesResponse.value = list
                }
            loading.value = DISMISS
        }
    }

    fun getPopularSeries() {
        viewModelScope.launch {
            getPopularTvShowsUseCase.execute()
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { list ->
                    tvShowsResponse.value = list
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
                    allContentResponse.value = list
                }
        }
    }

}