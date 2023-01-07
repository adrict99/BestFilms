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
        binding = FragmentMovieDetailBinding.bind(view)
        setupView()
        setupViewModelObservers()
        getMovieDetailData()
    }

    private fun getMovieDetailData() = movieDetailViewModel.getMovieDetail(args.movieId)

    private fun setupViewModelObservers() {
        //Observes error or loading status
        movieDetailViewModel.errorMessage.observe(viewLifecycleOwner) { handleError(it) }
        movieDetailViewModel.loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }
        //Observes movie or tv detailed data from API response
        movieDetailViewModel.movieDetailData.observe(viewLifecycleOwner) { setupDetailMovieInfo(it) }
    }

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

    private fun setupView() {
        setupRecyclerViews()
        //TODO: Add other methods to setup UI
    }

    private fun setupRecyclerViews() {
        //Setting up images and actors recyclerViews
    }

    private fun setupDetailMovieInfo(movieDetailResponse: MovieDetailResponse) {

    }

}