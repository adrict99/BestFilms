package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.adrict99.bestfilms.utils.extensions.setScrollListener
import com.adrict99.bestfilms.utils.extensions.setupAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMovieClickListener, OnContentClickListener, OnTvShowClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private val homeViewModel: HomeViewModel by lazy { viewModelFactory.get() }

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(requireContext(), this) }
    private val tvShowsAdapter: TvShowsAdapter by lazy { TvShowsAdapter(requireContext(), this) }
    private val allContentAdapter: ContentAdapter by lazy { ContentAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupView()
        setupViewModelObservers()
        getHomeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO: Make binding = null
    }

    private fun setupView() {
        setupRecyclerViews()
        setupSwipeRefreshLayout()
    }

    private fun setupRecyclerViews() {
        //Setting up all content, movies and series recyclerViews
        binding.apply {
            moviesRecyclerView.apply {
                setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
                adapter = movieAdapter
                /*setScrollListener(false) {
                    homeViewModel.apply {
                        if (this.currentMoviesPage < this.lastMoviesPage) {
                            this.currentMoviesPage ++
                            getMoviesData()
                        }
                    }
                }*/
            }
            tvShowsRecyclerView.apply {
                setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
                adapter = tvShowsAdapter
                /*setScrollListener(false) {
                    homeViewModel.apply {
                        if (this.currentTvShowsPage < this.lastTvShowsPage) {
                            this.currentTvShowsPage ++
                            getTvShowsData()
                        }
                    }
                }*/
            }
            allContentRecyclerView.apply {
                setupAdapter(LinearLayoutManager.HORIZONTAL, false, 16)
                adapter = allContentAdapter
                /*setScrollListener(false) {
                    homeViewModel.apply {
                        if (this.currentPopularContentPage < this.lastPopularContentPage) {
                            this.currentPopularContentPage ++
                            getPopularContentData()
                        }
                    }
                }*/
            }
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.fragmentHomeSwipeRefreshLayout.apply {
            setOnRefreshListener {
                getHomeData()
            }
        }
    }

    private fun getHomeData() {
        //API request for movies, series and tv shows
        homeViewModel.getHomeData()
    }

    private fun setupViewModelObservers() {
        homeViewModel.apply {
            //Observes errors and loading status
            errorMessage.observe(viewLifecycleOwner) { handleError(it) }
            loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }

            //Observes popular all content, movies and tv shows response
            contentResponse.observe(viewLifecycleOwner) {
                allContentAdapter.addAllContent(it.allContentResponse.results!!)
                movieAdapter.addAllMovies(it.moviesResponse.results!!)
                tvShowsAdapter.addAllTvShows(it.tvShowsResponse.results!!)
                if (isSwipeRefreshLoading()) binding.fragmentHomeSwipeRefreshLayout.isRefreshing =
                    false
            }
        }
    }

    private fun isSwipeRefreshLoading(): Boolean =
        binding.fragmentHomeSwipeRefreshLayout.isRefreshing

    override fun onMovieClicked(selectedMovie: Int?) {
        selectedMovie?.let { navigator.goToMovieDetail(it, this) }
    }

    override fun onContentClicked(selectedContent: Int?) {
        selectedContent?.let { navigator.goToMovieDetail(it, this) }
    }

    override fun onTvShowClicked(selectedTvShow: Int?) {
        selectedTvShow?.let { navigator.goToTvShowDetail(it, this) }
    }
}