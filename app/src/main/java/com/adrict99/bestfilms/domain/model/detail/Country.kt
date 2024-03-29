package com.adrict99.bestfilms.domain.model.detail

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)