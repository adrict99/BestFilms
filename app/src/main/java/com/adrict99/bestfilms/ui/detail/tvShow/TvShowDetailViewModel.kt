package com.adrict99.bestfilms.ui.detail.tvShow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.detail.TvShowDetailResponse
import com.adrict99.bestfilms.domain.model.media.tvShow.Network
import com.adrict99.bestfilms.domain.model.media.tvShow.ProductionCompany
import com.adrict99.bestfilms.domain.model.media.tvShow.Season
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationNetwork
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationProductionCompany
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationSeason
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationTvShow
import com.adrict99.bestfilms.domain.useCase.GetTvDetailUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import com.adrict99.bestfilms.ui.detail.tvShow.mapper.toPresentationModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvShowDetailViewModel @Inject constructor(
    private val getTvDetailUseCase: GetTvDetailUseCase
) : BaseViewModel() {

    //Stores TV show detail API data to be able to observe and use it
    val tvShowDetailData: MutableLiveData<PresentationTvShow> by lazy { MutableLiveData<PresentationTvShow>() }
    val tvShowSeasons: MutableLiveData<List<PresentationSeason>> by lazy { MutableLiveData<List<PresentationSeason>>() }
    val tvShowNetworks: MutableLiveData<List<PresentationNetwork>> by lazy { MutableLiveData<List<PresentationNetwork>>() }
    val tvShowProductionCompanies: MutableLiveData<List<PresentationProductionCompany>> by lazy { MutableLiveData<List<PresentationProductionCompany>>() }

    fun getTvShowDetail(id: Int) {
        loading.value = SHOW
        viewModelScope.launch {
            getTvDetailUseCase.execute(id)
                .catch { errorMessage.value = mapOf(0 to (it.message ?: "")) }
                .collect {
                    tvShowDetailData.value = it.toPresentationModel()
                    tvShowSeasons.value = it.seasons.toPresentationModel()
                    tvShowNetworks.value = it.networks.toPresentationModel()
                    tvShowProductionCompanies.value = it.productionCompanies.toPresentationModel()
                }
            loading.value = DISMISS
        }
    }
}