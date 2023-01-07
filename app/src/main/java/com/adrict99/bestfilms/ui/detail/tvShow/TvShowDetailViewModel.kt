package com.adrict99.bestfilms.ui.detail.tvShow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.domain.useCase.GetTvDetailUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvShowDetailViewModel @Inject constructor(
    private val getTvDetailUseCase: GetTvDetailUseCase
): BaseViewModel() {

    //Stores TV show detail API data to be able to observe and use it
    val tvShowDetailData: MutableLiveData<TvDetailResponse> by lazy { MutableLiveData<TvDetailResponse>() }

    fun getTvShowDetail(id: Int) {
        loading.value = SHOW
        viewModelScope.launch {
            getTvDetailUseCase.execute(id)
                .catch {
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect { data ->
                    tvShowDetailData.value = data
                }
            loading.value = DISMISS
        }
    }
}