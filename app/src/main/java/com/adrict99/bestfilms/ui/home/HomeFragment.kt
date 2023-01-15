package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentHomeBinding
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter.OnContentClickListener
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter.OnMovieClickListener
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter.OnTvShowClickListener
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.setupAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMovieClickListener, OnContentClickListener, OnTvShowClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private val homeViewModel: HomeViewModel by lazy { viewModelFactory.get() }

    private val allContentAdapter: ContentAdapter by lazy { ContentAdapter(requireContext(), this) }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(requireContext(), this) }
    private val tvShowsAdapter: TvShowsAdapter by lazy { TvShowsAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupRecyclerViews()
        setupViewModelObservers()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO: Make binding = null
    }

    private fun setupRecyclerViews() {
        //Setting up all content, movies and series recyclerViews
        binding.allContentRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
            adapter = allContentAdapter
        }
        binding.moviesRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
            adapter = movieAdapter
        }
        binding.tvShowsRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
            adapter = tvShowsAdapter
        }
    }

    private fun getData() {
        //API request for movies, series and tv shows
        //TODO: Refactor with Retrofit async API requests instead
        homeViewModel.getPopularAllContent()
        homeViewModel.getPopularMovies()
        homeViewModel.getPopularSeries()
    }

    private fun setupViewModelObservers() {
        homeViewModel.apply {
            //Observes errors and loading status
            errorMessage.observe(viewLifecycleOwner) { handleError(it) }
            loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }

            //Observes popular all content, movies and tv shows response
            allContentResponse.observe(viewLifecycleOwner) { allContentAdapter.addAllContent(it.results!!) }
            moviesResponse.observe(viewLifecycleOwner) { movieAdapter.addAllMovies(it.results!!) }
            tvShowsResponse.observe(viewLifecycleOwner) { tvShowsAdapter.addAllTvShows(it.results!!) }
        }
    }

    override fun onMovieClicked(selectedMovie: Int?) {
        selectedMovie?.let { navigator.goToMovieDetail(it,this) }
    }
    override fun onContentClicked(selectedContent: Int?) {
        selectedContent?.let { navigator.goToMovieDetail(it,this) }
    }
    override fun onTvShowClicked(selectedTvShow: Int?) {
        selectedTvShow?.let { navigator.goToTvShowDetail(it, this) }
    }
}