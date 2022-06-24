package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.BestFilmsApplication
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentHomeBinding
import com.adrict99.bestfilms.ui.MainActivity
import com.adrict99.bestfilms.ui.MainViewModel
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter.OnContentClickListener
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter.OnMovieClickListener
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter.OnTvShowClickListener
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupView()
        setupViewModelObservers()
        getDataFromApi()
    }

    private fun setupViewModelObservers() {
        //Observes popular all content, movies and tv shows response
        homeViewModel.popularAllContentResponse.observe(viewLifecycleOwner) {
            allContentAdapter.addAllContent(it.results!!) }
        homeViewModel.popularMoviesResponse.observe(viewLifecycleOwner) {
            movieAdapter.addAllMovies(it.results!!) }
        homeViewModel.popularTvShowsResponse.observe(viewLifecycleOwner) {
            tvShowsAdapter.addAllTvShows(it.results!!) }
    }

    private fun setupView() {
        setupRecyclerViews()
    }


    private fun getDataFromApi() {
        //API request for movies, series and tv shows
        homeViewModel.getPopularAllContent()
        homeViewModel.getPopularMovies()
        homeViewModel.getPopularSeries()
    }


    private fun setupRecyclerViews() {
        //Setting up all content recyclerView
        binding.allContentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = allContentAdapter
        }
        //Setting up movies recyclerView
        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        //Setting up series recyclerView
        binding.tvShowsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }

    override fun onMovieClicked(selectedMovie: Int) { Toast.makeText(requireContext(), selectedMovie, Toast.LENGTH_LONG).show() }
    override fun onContentClicked(selectedContent: Int) { Toast.makeText(requireContext(), selectedContent, Toast.LENGTH_LONG).show() }
    override fun onTvShowClicked(selectedTvShow: Int) { Toast.makeText(requireContext(), selectedTvShow, Toast.LENGTH_LONG).show() }
}