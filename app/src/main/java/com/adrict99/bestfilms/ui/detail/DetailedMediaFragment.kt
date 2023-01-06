package com.adrict99.bestfilms.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.data.network.response.detail.MovieDetailResponse
import com.adrict99.bestfilms.databinding.FragmentDetailedMediaBinding
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.utils.types.MediaType
import com.adrict99.bestfilms.utils.ViewModelFactory
import javax.inject.Inject

class DetailedMediaFragment : BaseFragment<FragmentDetailedMediaBinding>(R.layout.fragment_detailed_media) {

    private val args: DetailedMediaFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DetailedMediaViewModel>
    private val detailedMediaViewModel: DetailedMediaViewModel by lazy { viewModelFactory.get() }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedMediaBinding.bind(view)
        setupView()
        setupViewModelObservers()
        getDataFromApi()
    }

    private fun getDataFromApi() {
        when (args.mediaType) {
            MediaType.TYPE_MOVIE -> detailedMediaViewModel.getMovieDetail(args.mediaId)
            MediaType.TYPE_TV_SHOW -> detailedMediaViewModel.getTvDetail(args.mediaId)
        }
    }

    private fun setupViewModelObservers() {
        detailedMediaViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            handleError(error)
        }
        detailedMediaViewModel.loading.observe(viewLifecycleOwner) { showLoad ->
            manageLoadingDialog(showLoad)
        }
        //Observes movie or tv detailed data from API response
        if (args.mediaType == MediaType.TYPE_MOVIE) {
            detailedMediaViewModel.movieDetailData.observe(viewLifecycleOwner) { movieDetail ->
                setupDetailMediaInfo(movieDetail)
            }
        } else {
            detailedMediaViewModel.tvDetailData.observe(viewLifecycleOwner) { tvDetail ->
                setupDetailMediaInfo(tvDetail)
            }
        }
    }

    private fun setupView() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        //Setting up images and actors recyclerViews
    }

    private fun setupDetailMediaInfo(mediaInfo: Any) {

    }

}