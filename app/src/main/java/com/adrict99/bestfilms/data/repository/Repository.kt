package com.adrict99.bestfilms.data.repository

import com.adrict99.bestfilms.utils.NetworkUtils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class Repository(
    open val networkUtils: NetworkUtils
) {

    fun <T : Any> callApi(call: suspend () -> T): Flow<T> = flow {

        if (!networkUtils.hasConnection())
            throw Exception(0.toString(), "Error, no tiene conexión.")


        val response = call.invoke()
        if (response.isSuccessful) {
            emit(response.b)
        } else {
            /*
            controlar codigos de error
                    if (myResp.code() == 403){
                        CustomException(response.code(), "mensaje de error custom")
                    }

            */
            val gson = Gson()
            response.errorBody()?.let {
                val errorResponse: ErrorResponse
                try {
                    errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                } catch (e: Exception) {
                    throw CustomException(response.code(), "Error ${response.code()}")
                }
                errorResponse?.let {
                    throw CustomException(errorResponse.status, errorResponse.error)
                }

            }
            throw CustomException(response.code(), "Error ${response.code()}")

        }

    }

}
