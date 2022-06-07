package com.adrict99.bestfilms.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.ActivityMainBinding
import com.adrict99.bestfilms.ui.common.BaseActivity
import com.adrict99.bestfilms.utils.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    val mainViewModel: MainViewModel by lazy { viewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController, false)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

//    private fun setupBottomNavigationView() {
//        val bottomNavigationView = binding.bottomNavigationView
//        val navGraphs = listOf(R.navigation.nav_graph)
//
//        val controller = bottomNavigationView.setupWithNavController(
//            navGraphs,
//            supportFragmentManager,
//            R.id.main_nav_host_fragment,
//            intent
//        )
//    }
}