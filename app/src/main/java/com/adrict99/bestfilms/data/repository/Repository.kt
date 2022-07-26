package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.data.network.response.ErrorResponse
import com.adrict99.bestfilms.utils.NetworkUtils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class Repository(
    open val networkUtils: NetworkUtils
) {

    fun <T : Any> callApi(call: suspend () -> Response<T>): Flow<T> = flow {

        if (!networkUtils.hasConnection()) throw Exception("Sin conexión")

        val response = call.invoke()
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            val gson = Gson()
            response.errorBody()?.let { responseBody ->
                val errorResponse: ErrorResponse
                try {
                    errorResponse = gson.fromJson(responseBody.toString(), ErrorResponse::class.java)
                } catch (e: Exception) {
                    throw Exception("${response.code()}")
                }
                errorResponse?.let { error ->
                    throw Exception("${error.status} ${error.error}")
                }

            }
            throw Exception("${response.code()}")

        }

    }

}
