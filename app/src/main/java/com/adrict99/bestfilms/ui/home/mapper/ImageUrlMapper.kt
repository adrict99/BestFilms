package com.adrict99.bestfilms.ui.home.mapper

import com.adrict99.bestfilms.BuildConfig

fun String.addBaseImageUrl(): String = "${BuildConfig.IMAGE_BASE_URL}$this"