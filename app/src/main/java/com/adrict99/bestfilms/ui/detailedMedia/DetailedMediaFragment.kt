package com.adrict99.bestfilms.ui.detailedMedia

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentDetailedMediaBinding
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.utils.MediaType
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.fromUrl
import javax.inject.Inject

class DetailedMediaFragment : BaseFragment<FragmentDetailedMediaBinding>(R.layout.fragment_detailed_media) {

    private val args: DetailedMediaFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DetailedMediaViewModel>
    private val detailedMediaViewModel: DetailedMediaViewModel by lazy { viewModelFactory.get() }

    //Adapters

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
        //Observes movie or tv detailed data from API response
        if (args.mediaType == MediaType.TYPE_MOVIE) {
            detailedMediaViewModel.movieDetailData.observe(viewLifecycleOwner) {
                detailedMediaViewModel.movieDetailData.value?.backdropPath?.let { url ->
                    binding.fragmentDetailedMediaBackpathImage.fromUrl(url)
                }
            }
        } else {
            detailedMediaViewModel.tvDetailData.observe(viewLifecycleOwner) {

            }
        }
    }

    private fun setupView() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        //Setting up images and actors recyclerViews
    }

}