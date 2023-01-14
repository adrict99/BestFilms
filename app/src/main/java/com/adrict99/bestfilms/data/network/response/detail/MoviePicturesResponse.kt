package com.adrict99.bestfilms.data.network.response.detail

import com.google.gson.annotations.SerializedName

data class MoviePicturesResponse(
    @SerializedName("backdrops")
    val backdrops: List<String>,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logos")
    val logos: List<String>,
    @SerializedName("posters")
    val posters: List<String>
)