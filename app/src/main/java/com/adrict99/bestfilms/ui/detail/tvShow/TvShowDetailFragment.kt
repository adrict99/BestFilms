package com.adrict99.bestfilms.ui.detail.tvShow

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.data.network.response.detail.TvDetailResponse
import com.adrict99.bestfilms.databinding.FragmentTvShowDetailBinding
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.utils.ViewModelFactory
import javax.inject.Inject

class TvShowDetailFragment :
    BaseFragment<FragmentTvShowDetailBinding>(R.layout.fragment_tv_show_detail) {

    private val args: TvShowDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TvShowDetailViewModel>
    private val tvShowDetailViewModel: TvShowDetailViewModel by lazy { viewModelFactory.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTvShowDetailBinding.bind(view)
        setupView()
        setupViewModelObservers()
        getTvShowDetailData()
    }

    private fun getTvShowDetailData() = tvShowDetailViewModel.getTvShowDetail(args.tvShowId)

    private fun setupViewModelObservers() {
        //Observes error or loading status
        tvShowDetailViewModel.errorMessage.observe(viewLifecycleOwner) { handleError(it) }
        tvShowDetailViewModel.loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }
        //Observes movie or tv detailed data from API response
        tvShowDetailViewModel.tvShowDetailData.observe(viewLifecycleOwner) { setupDetailTvShowInfo(it) }
    }

    private fun setupView() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        //Setting up images and actors recyclerViews
    }

    private fun setupDetailTvShowInfo(tvShowDetailResponse: TvDetailResponse) {

    }

}