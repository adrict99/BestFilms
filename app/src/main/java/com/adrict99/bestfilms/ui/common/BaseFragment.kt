package com.adrict99.bestfilms.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.adrict99.bestfilms.utils.dismissLoadingDialog
import com.adrict99.bestfilms.utils.showLoadingDialog

abstract class BaseFragment<ViewBinding: ViewDataBinding>: Fragment() {

    protected lateinit var binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateLayout(layoutInflater)

        return binding.root
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): ViewBinding

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.apply {
            loading.observe(viewLifecycleOwner) { loadingStatus ->
                manageLoadingDialog(loadingStatus)
            }
        }
    }

    protected open fun manageLoadingDialog(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }*/

}