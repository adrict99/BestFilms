package com.adrict99.bestfilms.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentSearchBinding
import com.adrict99.bestfilms.domain.model.search.PresentationSearchResult
import com.adrict99.bestfilms.domain.model.search.SearchResult
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.ui.favorites.FavoritesViewModel
import com.adrict99.bestfilms.ui.home.HomeViewModel
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter
import com.adrict99.bestfilms.ui.search.adapter.ResultAdapter
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.extensions.setupAdapter
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
ResultAdapter.OnResultClickListener{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchViewModel>
    private val searchViewModel: SearchViewModel by lazy { viewModelFactory.get() }

    private val resultsAdapter: ResultAdapter by lazy { ResultAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setupRecyclerView()
        setupLocalObservers()
        setupViewModelObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO: Make binding = null
    }

    private fun setupLocalObservers() {
        binding.fragmentSearchAutoCompleteTextView.doOnTextChanged { text, _, _, _ ->
            //TODO: Include infinite scroll with pagination
            searchViewModel.getSearchResults(text.toString(), 1)
        }
    }

    private fun setupViewModelObservers() {
        searchViewModel.searchResults.observe(viewLifecycleOwner) { results ->
            resultsAdapter.addAllResults(results)
            results.map {
                Log.d("resultsTest 1", "${it.title}")
            }
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentSearchResultsRecyclerView.apply {
            setupAdapter(LinearLayoutManager.VERTICAL, false, 16)
            adapter = resultsAdapter
        }
    }

    override fun onResultClicked(result: PresentationSearchResult) {
        //TODO: Create custom ENUM Movie/TvShow
        if (result.type.equals("movie")) {
            result.id?.let { navigator.goToMovieDetail(it,this) }
        } else {
            result.id?.let { navigator.goToTvShowDetail(it,this) }
        }
    }

}