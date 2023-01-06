package com.adrict99.bestfilms.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.ActivityMainBinding
import com.adrict99.bestfilms.ui.common.BaseActivity
import dagger.android.AndroidInjection

class MainActivity : BaseActivity<ActivityMainBinding>() {

    /*@Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    val mainViewModel: MainViewModel by lazy { viewModelFactory.get() }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        setSupportActionBar(binding.appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val navView = binding.bottomNavigationView
        val navController = findNavController(R.id.main_nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search,
                R.id.navigation_favorites
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

}