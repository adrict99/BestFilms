package com.adrict99.bestfilms.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.detail.MovieCreditsResponse
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.data.network.response.detail.MoviePicturesResponse
import com.adrict99.bestfilms.domain.model.detail.Cast
import com.adrict99.bestfilms.domain.model.picture.Picture
import com.adrict99.bestfilms.domain.useCase.GetMovieCreditsUseCase
import com.adrict99.bestfilms.domain.useCase.GetMovieDetailUseCase
import com.adrict99.bestfilms.domain.useCase.GetMoviePicturesUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import com.adrict99.bestfilms.ui.detail.movie.mapper.toPresentationModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMoviePicturesUseCase: GetMoviePicturesUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
): BaseViewModel() {

    //Stores movie detail API data to be able to observe and use it
    val movieDetailData: MutableLiveData<MovieDetailResponse> by lazy { MutableLiveData<MovieDetailResponse>() }
    val moviePictures: MutableLiveData<List<Picture>> by lazy { MutableLiveData<List<Picture>>() }
    val movieActors: MutableLiveData<List<Cast>> by lazy { MutableLiveData<List<Cast>>() }

    fun getMovieDetail(id: Int) {
        loading.value = SHOW
        viewModelScope.launch {
            getMovieDetailUseCase.execute(id)
                .catch { errorMessage.value = mapOf(0 to (it.message ?: "")) }
                .collect { movieDetailData.value = it }
            loading.value = DISMISS
        }
    }

    fun getMoviePictures(id: Int) {
        viewModelScope.launch {
            getMoviePicturesUseCase.execute(id)
                .catch { errorMessage.value = mapOf(0 to (it.message ?: "")) }
                .collect { moviePictures.value = it.toPresentationModel() }
        }
    }

    fun getMovieActors(id: Int) {
        viewModelScope.launch {
            getMovieCreditsUseCase.execute(id)
                .catch { errorMessage.value = mapOf(0 to (it.message ?: "")) }
                .collect { movieActors.value = it.toPresentationModel() }
        }
    }

}