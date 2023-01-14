package com.adrict99.bestfilms.data.network.response.detail

import com.adrict99.bestfilms.domain.model.detail.Cast
import com.adrict99.bestfilms.domain.model.detail.Crew
import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int?
)