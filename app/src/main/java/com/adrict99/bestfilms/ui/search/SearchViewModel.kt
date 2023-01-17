package com.adrict99.bestfilms.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrict99.bestfilms.data.network.response.search.MultiSearchResponse
import com.adrict99.bestfilms.domain.model.search.PresentationSearchResult
import com.adrict99.bestfilms.domain.useCase.GetSearchResultsUseCase
import com.adrict99.bestfilms.ui.common.BaseViewModel
import com.adrict99.bestfilms.ui.search.mapper.toPresentationModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : BaseViewModel() {

    //Stores search results to observe them
    val searchResults: MutableLiveData<List<PresentationSearchResult>> by lazy { MutableLiveData<List<PresentationSearchResult>>() }

    fun getSearchResults(query: String, page: Int? = 1) {
        viewModelScope.launch {
            getSearchResultsUseCase.execute(query, 1)
                .catch {
                    Log.d("resultsTest 3", "error")
                    errorMessage.value = mapOf(0 to (it.message ?: ""))
                }
                .collect {
                    Log.d("resultsTest 2", "${it.results}")
                    searchResults.value = it.toPresentationModel()
                }
        }
    }

}