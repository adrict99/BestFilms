package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
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
import com.adrict99.bestfilms.utils.types.MediaType
import com.adrict99.bestfilms.utils.ViewModelFactory
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMovieClickListener, OnContentClickListener, OnTvShowClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private val homeViewModel: HomeViewModel by lazy { viewModelFactory.get() }

    private val allContentAdapter: ContentAdapter by lazy { ContentAdapter(requireContext(), this) }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(requireContext(), this) }
    private val tvShowsAdapter: TvShowsAdapter by lazy { TvShowsAdapter(requireContext(), this) }

    private var actionNavigateToDetailedMedia: NavDirections? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupView()
        setupObservers()
        setupOnClickListeners()
        setupRecyclerViews()
        getDataFromApi()
    }

    private fun setupObservers() {
        //Observes errors and loading status
        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            handleError(it)
        }
        homeViewModel.loading.observe(viewLifecycleOwner) {
            manageLoadingDialog(it)
        }

        //Observes popular all content, movies and tv shows response
        homeViewModel.popularAllContentResponse.observe(viewLifecycleOwner) {
            allContentAdapter.addAllContent(it.results!!) }
        homeViewModel.popularMoviesResponse.observe(viewLifecycleOwner) {
            movieAdapter.addAllMovies(it.results!!) }
        homeViewModel.popularTvShowsResponse.observe(viewLifecycleOwner) {
            tvShowsAdapter.addAllTvShows(it.results!!) }
    }

    private fun setupOnClickListeners() {

    }

    private fun setupView() {

    }

    private fun getDataFromApi() {
        //API request for movies, series and tv shows
        //TODO: Refactor with Retrofit async API requests instead
        homeViewModel.getPopularAllContent()
        homeViewModel.getPopularMovies()
        homeViewModel.getPopularSeries()
    }

    private fun setupRecyclerViews() {
        //Setting up all content, movies and series recyclerViews
        binding.allContentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = allContentAdapter
        }
        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        binding.tvShowsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }

    private fun navigateToDetailedMedia(mediaId: Int, type: MediaType) {
        actionNavigateToDetailedMedia = HomeFragmentDirections.actionHomeFragmentToDetailedMediaFragment(
            mediaId = mediaId,
            mediaType = type
        )
        findNavController().navigate(actionNavigateToDetailedMedia!!)
    }

    override fun onMovieClicked(selectedMovie: Int?) {
        selectedMovie?.let { navigateToDetailedMedia(it, MediaType.TYPE_MOVIE) }
    }
    override fun onContentClicked(selectedContent: Int?) {
        selectedContent?.let { navigateToDetailedMedia(it, MediaType.TYPE_MOVIE) }
    }
    override fun onTvShowClicked(selectedTvShow: Int?) {
        selectedTvShow?.let { navigateToDetailedMedia(it, MediaType.TYPE_TV_SHOW) }
    }
}