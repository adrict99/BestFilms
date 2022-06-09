package com.adrict99.bestfilms.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentHomeBinding
import com.adrict99.bestfilms.ui.MainActivity
import com.adrict99.bestfilms.ui.MainViewModel
import com.adrict99.bestfilms.ui.home.adapter.HomeAdapter
import com.adrict99.bestfilms.ui.home.adapter.HomeAdapter.OnItemClickListener
import com.adrict99.bestfilms.utils.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    //TODO: Refactor this viewModel to HomeViewModel (the one for this fragment)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: MainViewModel

    //val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel = (activity as MainActivity).mainViewModel

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.popularMoviesResponse.observe(viewLifecycleOwner) {
            homeAdapter.addAllItems(it.results!!)
        }
        viewModel.getPopularMovies()
    }

    private fun setupRecyclerView() {
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    override fun onItemClicked(selectedItem: Int) {
        Toast.makeText(requireContext(), selectedItem, Toast.LENGTH_LONG).show()
    }
}