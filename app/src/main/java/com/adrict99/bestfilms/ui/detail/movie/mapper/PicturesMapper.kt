package com.adrict99.bestfilms.ui.detail.movie.mapper

import com.adrict99.bestfilms.data.network.response.detail.MoviePicturesResponse
import com.adrict99.bestfilms.domain.model.picture.Picture

fun MoviePicturesResponse.toPresentationModel(): List<Picture> = this.posters.map { Picture(url = it) }