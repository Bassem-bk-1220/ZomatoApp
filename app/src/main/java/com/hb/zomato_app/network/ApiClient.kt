package com.hb.zomato_app.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val SERVER_TIMEOUT = 30L
    private val BASE_URL = "https://developers.zomato.com/api/v2.1/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    fun getApiService(): ApiServices = getRetrofit().create(ApiServices::class.java)


}