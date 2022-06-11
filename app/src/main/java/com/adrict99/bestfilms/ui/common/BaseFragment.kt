package com.adrict99.bestfilms.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.adrict99.bestfilms.utils.dismissLoadingDialog
import com.adrict99.bestfilms.utils.showLoadingDialog

abstract class BaseFragment<ViewBinding: ViewDataBinding, ViewModel: BaseViewModel>: Fragment() {

    protected lateinit var binding: ViewBinding
    protected lateinit var viewModel: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        /*binding.apply {
            setVariable(BR.viewModel, viewModel)
            binding.lifecycleOwner = viewLifecycleOwner
            root.isClickable = true
            executePendingBindings()
        }*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
    }

}