package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentHomeBinding
import com.adrict99.bestfilms.ui.MainActivity
import com.adrict99.bestfilms.ui.MainViewModel
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter
import com.adrict99.bestfilms.ui.home.adapter.ContentAdapter.OnContentClickListener
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter
import com.adrict99.bestfilms.ui.home.adapter.MovieAdapter.OnMovieClickListener
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter
import com.adrict99.bestfilms.ui.home.adapter.TvShowsAdapter.OnTvShowClickListener
import javax.inject.Inject

class HomeFragment : Fragment(), OnMovieClickListener, OnContentClickListener, OnTvShowClickListener {

    private lateinit var binding: FragmentHomeBinding

    //TODO: Refactor this viewModel to HomeViewModel (the one for this fragment)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: MainViewModel

    //val viewModel: HomeViewModel by viewModels()

    private val allContentAdapter: ContentAdapter by lazy { ContentAdapter(requireContext(), this) }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(requireContext(), this) }
    private val tvShowsAdapter: TvShowsAdapter by lazy { TvShowsAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel = (activity as MainActivity).mainViewModel

        setupView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservers()
        getDataFromApi()
    }

    private fun setupViewModelObservers() {
        //Observes popular all content response
        viewModel.popularAllContentResponse.observe(viewLifecycleOwner) { allContentAdapter.addAllContent(it.results!!) }
        //Observes popular movies response
        viewModel.popularMoviesResponse.observe(viewLifecycleOwner) { movieAdapter.addAllMovies(it.results!!) }
        //Observes popular tv shows response
        viewModel.popularTvShowsResponse.observe(viewLifecycleOwner) { tvShowsAdapter.addAllTvShows(it.results!!) }
    }

    private fun setupView() {
        setupRecyclerViews()
    }


    private fun getDataFromApi() {
        //API request for movies, series and tv shows
        viewModel.getPopularAllContent()
        viewModel.getPopularMovies()
        viewModel.getPopularSeries()
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