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
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getPopularAllContentUseCase: GetPopularAllContentUseCase
) : BaseViewModel() {

    //Storing responses from the API requests
    val moviesResponse: MutableLiveData<MoviesResponse> by lazy { MutableLiveData<MoviesResponse>() }
    val tvShowsResponse: MutableLiveData<TvShowsResponse> by lazy { MutableLiveData<TvShowsResponse>() }
    val allContentResponse: MutableLiveData<AllContentResponse> by lazy { MutableLiveData<AllContentResponse>() }

    /*async creates a new coroutine, await suspends current coroutine and waits for the result,
        first return the network request responses emitted by the flow*/
    fun getHomeData() {
        loading.value = SHOW
        viewModelScope.launch {
            val job1 = async { getPopularMoviesUseCase.execute().first() }
            val job2 = async { getPopularTvShowsUseCase.execute().first() }
            val job3 = async { getPopularAllContentUseCase.execute().first() }
            try {
                moviesResponse.value = job1.await()
                tvShowsResponse.value = job2.await()
                allContentResponse.value = job3.await()
            } catch (e: Exception) {
                errorMessage.value = mapOf(0 to (e.message ?: ""))
            }
            loading.value = DISMISS
        }
    }


}