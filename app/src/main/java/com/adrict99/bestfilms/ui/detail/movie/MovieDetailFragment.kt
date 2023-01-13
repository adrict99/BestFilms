package com.adrict99.bestfilms.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.databinding.FragmentMovieDetailBinding
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.utils.ViewModelFactory
import javax.inject.Inject

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MovieDetailViewModel>
    private val movieDetailViewModel: MovieDetailViewModel by lazy { viewModelFactory.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureBinding(view)
        setupView()
        setupViewModelObservers()
        getMovieDetailData()
    }

    private fun configureBinding(view: View) {
        binding = FragmentMovieDetailBinding.bind(view)
        binding.viewModel = movieDetailViewModel
        binding.lifecycleOwner = this
    }

    private fun setupView() {
        setupRecyclerViews()
    }

    private fun setupViewModelObservers() {
        //Observes error or loading status
        movieDetailViewModel.errorMessage.observe(viewLifecycleOwner) { handleError(it) }
        movieDetailViewModel.loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }
    }

    private fun getMovieDetailData() = movieDetailViewModel.getMovieDetail(args.movieId)

    //TODO: Need to navigate to a future ImagesDetailFragment
    /*private val imagesAdapter: DetailImagesAdapter by lazy {
        DetailImagesAdapter(requireContext()) { image ->
            navigator.goToImagesDetail(this, image.id)
        }
    }*/

    //TODO: Need to navigate to a future ActorsDetailFragment
    /*private val actorsAdapter: DetailActorsAdapter by lazy {
        DetailActorsAdapter(requireContext()) { actor, image ->
            navigator.goToActorsDetail(this, actor.id, image)
        }
    }*/

    private fun setupRecyclerViews() {
        //TODO: Setting up images and actors recyclerViews
    }

}