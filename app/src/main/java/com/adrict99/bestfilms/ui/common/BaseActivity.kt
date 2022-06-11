package com.adrict99.bestfilms.ui.common

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.adrict99.bestfilms.utils.ViewModelFactory
import com.adrict99.bestfilms.utils.dismissLoadingDialog
import com.adrict99.bestfilms.utils.showLoadingDialog
import dagger.android.AndroidInjection

abstract class BaseActivity<V: ViewBinding/*, ViewModel: BaseViewModel*/>: AppCompatActivity() {

//    @Inject
//    lateinit var sharedPrefs: SharedPrefs

    lateinit var binding: V
    //lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        //setupObservers()
    }

    abstract fun getViewBinding(): V

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
        ViewModelProvider(this@BaseActivity, this)[T::class.java]

    override fun onStop() {
        super.onStop()
        manageLoadingDialog(false)
    }

    //private fun setupObservers() {}

    protected open fun manageLoadingDialog(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }

    fun showErrorMessage(it: Map<Int, String>) {
        Toast.makeText(applicationContext, it.values.first(), Toast.LENGTH_LONG).show()
//        when (it.keys.first()) {
//            401 -> logout()
//        }
    }
}