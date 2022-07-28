package com.adrict99.bestfilms.ui.detailedMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.domain.useCase.GetMovieDetailUseCase
import com.adrict99.bestfilms.domain.useCase.GetTvDetailUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedMediaViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTvDetailUseCase: GetTvDetailUseCase
): BaseViewModel() {
    //Stores API data to be able to observe and use it
    val movieDetailData: MutableLiveData<MovieDetailResponse> by lazy { MutableLiveData<MovieDetailResponse>() }
    val tvDetailData: MutableLiveData<TvDetailResponse> by lazy { MutableLiveData<TvDetailResponse>() }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.execute(id)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { data ->
                    movieDetailData.value = data
                }
        }
    }

    fun getTvDetail(id: Int) {
        viewModelScope.launch {
            getTvDetailUseCase.execute(id)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { data ->
                    tvDetailData.value = data
                }
        }
    }
}