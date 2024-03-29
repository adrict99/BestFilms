package com.adrict99.bestfilms.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentMovieDetailBinding
import com.adrict99.bestfilms.domain.model.Picture
import com.adrict99.bestfilms.domain.model.media.movie.presentation.PresentationActor
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.ui.detail.movie.adapter.ActorsAdapter
import com.adrict99.bestfilms.ui.detail.movie.adapter.PicturesAdapter
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.extensions.changeExpandableMode
import com.adrict99.bestfilms.utils.extensions.setupAdapter
import javax.inject.Inject

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail),
    PicturesAdapter.OnPictureClickListener, ActorsAdapter.OnActorsClickListener {

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MovieDetailViewModel>
    private val movieDetailViewModel: MovieDetailViewModel by lazy { viewModelFactory.get() }

    private val picturesAdapter: PicturesAdapter by lazy { PicturesAdapter(requireContext(), this) }
    private val actorsAdapter: ActorsAdapter by lazy { ActorsAdapter(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureBinding(view)
        setupView()
        setupViewModelObservers()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO: Make binding = null
    }

    private fun configureBinding(view: View) {
        binding = FragmentMovieDetailBinding.bind(view)
        binding.viewModel = movieDetailViewModel
        binding.lifecycleOwner = this@MovieDetailFragment.viewLifecycleOwner
    }

    private fun setupView() {
        setupRecyclerViews()
        setOnClickListeners()
    }

    private fun setupViewModelObservers() {
        movieDetailViewModel.apply {
            //Observes error or loading status
            errorMessage.observe(viewLifecycleOwner) { handleError(it) }
            loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }

            //Observes movie pictures and actors
            movieDetailData.observe(viewLifecycleOwner) {
                if (it.overview?.isEmpty() == true)
                    binding.fragmentMovieDetailStoryLinearLayout.visibility = View.GONE
            }
            movieActors.observe(viewLifecycleOwner) {
                actorsAdapter.addAllActors(it)
                if (it.isEmpty())
                    binding.fragmentMovieDetailActorsLinearLayout.visibility = View.GONE
            }
            moviePictures.observe(viewLifecycleOwner) {
                picturesAdapter.addAllPictures(it)
                if (it.isEmpty())
                    binding.fragmentMovieDetailImagesLinearLayout.visibility = View.GONE
            }
        }
    }

    private fun getData() {
        //API request for detail data, actors and pictures
        //TODO: Refactor with Retrofit async API requests instead
        getMovieDetailData()
        getMovieActors()
        getMoviePictures()
    }

    private fun getMovieDetailData() = movieDetailViewModel.getMovieDetail(args.movieId)
    private fun getMovieActors() = movieDetailViewModel.getMovieActors(args.movieId)
    private fun getMoviePictures() = movieDetailViewModel.getMoviePictures(args.movieId)

    //TODO: Need to navigate to a future MoviePicturesFragment (swipe through pictures)
    /*private val imagesAdapter: DetailImagesAdapter by lazy {
        DetailImagesAdapter(requireContext()) { image ->
            navigator.goToImagesDetail(this, image.id)
        }
    }*/

    //TODO: Need to navigate to a future ActorsPicturesFragment (swipe through pictures)
    /*private val actorsAdapter: DetailActorsAdapter by lazy {
        DetailActorsAdapter(requireContext()) { actor, image ->
            navigator.goToActorsDetail(this, actor.id, image)
        }
    }*/

    private fun setupRecyclerViews() {
        //Setting up actors and pictures recyclerViews
        binding.fragmentMovieDetailActorsRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 24)
            adapter = actorsAdapter
        }
        binding.fragmentMovieDetailImagesRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 24)
            adapter = picturesAdapter
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            fragmentMovieDetailStoryShowMoreTextView.changeExpandableMode(
                fragmentMovieDetailStoryContentTextView
            )
        }
    }

    override fun onPictureClicked(selectedPicture: Picture) {
        TODO("Not yet implemented")
    }

    override fun onActorClicked(selectedPresentationActor: MutableList<PresentationActor>) {
        TODO("Not yet implemented")
    }

}