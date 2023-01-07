package com.adrict99.bestfilms.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.domain.useCase.GetMovieDetailUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): BaseViewModel() {

    //Stores movie detail API data to be able to observe and use it
    val movieDetailData: MutableLiveData<MovieDetailResponse> by lazy { MutableLiveData<MovieDetailResponse>() }

    fun getMovieDetail(id: Int) {
        loading.value = SHOW
        viewModelScope.launch {
            getMovieDetailUseCase.execute(id)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { data ->
                    movieDetailData.value = data
                }
            loading.value = DISMISS
        }
    }

}