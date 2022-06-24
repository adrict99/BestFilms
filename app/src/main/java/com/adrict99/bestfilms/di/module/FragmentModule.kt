package com.adrict99.bestfilms.di.module

import com.adrict99.bestfilms.ui.favorites.FavoritesFragment
import com.adrict99.bestfilms.ui.home.HomeFragment
import com.adrict99.bestfilms.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindsHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindsSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun bindsFavoritesFragment(): FavoritesFragment
}