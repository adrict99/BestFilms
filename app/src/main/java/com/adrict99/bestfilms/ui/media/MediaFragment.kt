package com.adrict99.bestfilms.ui.media

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.FragmentMediaBinding
import com.adrict99.bestfilms.ui.HomeViewModel
import javax.inject.Inject

class MediaFragment : Fragment() {

    private lateinit var binding: FragmentMediaBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var loginViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}