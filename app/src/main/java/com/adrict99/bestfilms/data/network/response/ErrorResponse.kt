package com.adrict99.bestfilms.data.network.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String = "",
    @SerializedName("status")
    val status: Int = 0
)
