package com.adrict99.bestfilms.ui.common

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.adrict99.bestfilms.utils.DialogUtil
import com.adrict99.bestfilms.utils.ViewModelFactory
import dagger.android.AndroidInjection

abstract class BaseActivity<V: ViewBinding>: AppCompatActivity() {

//    @Inject
//    lateinit var sharedPrefs: SharedPrefs

    open val progressDialog: Dialog by lazy { DialogUtil().showLoadingDialog(this) }

    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): V

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
        ViewModelProvider(this@BaseActivity, this).get(T::class.java)

    override fun onStop() {
        super.onStop()
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    fun showLoadingDialog(mustShow: Boolean) {
        if (mustShow)
            progressDialog.show()
        else
            progressDialog.dismiss()
    }

    fun showErrorMessage(it: Map<Int, String>) {
        Toast.makeText(applicationContext, it.values.first(), Toast.LENGTH_LONG).show()
//        when (it.keys.first()) {
//            401 -> logout()
//        }
    }
}