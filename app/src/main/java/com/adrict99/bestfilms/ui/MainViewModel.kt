package com.adrict99.bestfilms.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.PopularMoviesResponse
import com.adrict99.bestfilms.domain.useCase.GetPopularMoviesUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): BaseViewModel() {

    val popularMoviesResponse: MutableLiveData<PopularMoviesResponse> by lazy {
        MutableLiveData<PopularMoviesResponse>()
    }

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

}