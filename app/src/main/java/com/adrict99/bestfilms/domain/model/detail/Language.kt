package com.adrict99.bestfilms.domain.model.detail

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)