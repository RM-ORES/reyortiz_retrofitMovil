package com.example.retrofitMovil.data.sources

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key", "8979fea4ecc57850778fa624133234d8")
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}