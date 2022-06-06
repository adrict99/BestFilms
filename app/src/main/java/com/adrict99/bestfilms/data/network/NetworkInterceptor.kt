package com.adrict99.bestfilms.data.network

import com.adrict99.bestfilms.utils.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Credentials.URL_PARAM_API_KEY, Credentials.TMDB_API_KEY)
            .addQueryParameter(Credentials.URL_PARAM_LANGUAGE, Credentials.TMDB_ES_LANGUAGE)
            .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}