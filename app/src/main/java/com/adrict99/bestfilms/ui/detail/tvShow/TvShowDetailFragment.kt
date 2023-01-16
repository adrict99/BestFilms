package com.adrict99.bestfilms.ui.detail.tvShow

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentTvShowDetailBinding
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationSeason
import com.adrict99.bestfilms.ui.common.BaseFragment
import com.adrict99.bestfilms.ui.detail.tvShow.adapter.CompaniesAdapter
import com.adrict99.bestfilms.ui.detail.tvShow.adapter.NetworksAdapter
import com.adrict99.bestfilms.ui.detail.tvShow.adapter.SeasonsAdapter
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.extensions.changeExpandableMode
import com.adrict99.bestfilms.utils.extensions.setupAdapter
import javax.inject.Inject

class TvShowDetailFragment :
    BaseFragment<FragmentTvShowDetailBinding>(R.layout.fragment_tv_show_detail),
    SeasonsAdapter.OnSeasonClickListener {

    private val args: TvShowDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TvShowDetailViewModel>
    private val tvShowDetailViewModel: TvShowDetailViewModel by lazy { viewModelFactory.get() }

    private val seasonsAdapter: SeasonsAdapter by lazy { SeasonsAdapter(requireContext(), this) }
    private val networksAdapter: NetworksAdapter by lazy { NetworksAdapter(requireContext()) }
    private val companiesAdapter: CompaniesAdapter by lazy { CompaniesAdapter(requireContext()) }

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
        binding = FragmentTvShowDetailBinding.bind(view)
        binding.viewModel = tvShowDetailViewModel
        binding.lifecycleOwner = this@TvShowDetailFragment.viewLifecycleOwner
    }

    private fun setupView() {
        setupRecyclerViews()
        setupOnClickListeners()
    }

    private fun setupViewModelObservers() {
        tvShowDetailViewModel.apply {
            //Observes error or loading status
            errorMessage.observe(viewLifecycleOwner) { handleError(it) }
            loading.observe(viewLifecycleOwner) { manageLoadingDialog(it) }

            //Observes seasons, networks and production companies
            tvShowDetailData.observe(viewLifecycleOwner) {
                if (it.overview?.isEmpty() == true)
                    binding.fragmentTvShowDetailStoryLinearLayout.visibility = View.GONE
            }
            tvShowSeasons.observe(viewLifecycleOwner) {
                seasonsAdapter.addAllSeasons(it)
                if (it.isEmpty()) binding.fragmentTvShowDetailSeasonsLinearLayout.visibility = View.GONE
            }
            tvShowNetworks.observe(viewLifecycleOwner) {
                networksAdapter.addAllNetworks(it)
                if (it.isEmpty()) binding.fragmentTvShowDetailNetworksLinearLayout.visibility = View.GONE
            }
            tvShowProductionCompanies.observe(viewLifecycleOwner) {
                companiesAdapter.addAllCompanies(it)
                if (it.isEmpty()) binding.fragmentTvShowDetailCompaniesLinearLayout.visibility = View.GONE
            }
        }
    }

    private fun getData() = getTvShowDetailData()

    private fun getTvShowDetailData() = tvShowDetailViewModel.getTvShowDetail(args.tvShowId)

    private fun setupRecyclerViews() {
        //Setting up seasons, networks and companies
        binding.fragmentTvShowDetailSeasonsRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 24)
            adapter = seasonsAdapter
        }
        binding.fragmentTvShowDetailNetworksRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 24)
            adapter = networksAdapter
        }
        binding.fragmentTvShowDetailCompaniesRecyclerView.apply {
            setupAdapter(LinearLayoutManager.HORIZONTAL, false, 24)
            adapter = companiesAdapter
        }
    }

    private fun setupOnClickListeners() {
        binding.apply {
            fragmentTvShowDetailShowMoreTextView.changeExpandableMode(
                fragmentTvShowDetailStoryContentTextView
            )
        }
    }

    override fun onSeasonClicked(selectedPresentationSeason: MutableList<PresentationSeason>) {
        TODO("Not yet implemented")
    }

}