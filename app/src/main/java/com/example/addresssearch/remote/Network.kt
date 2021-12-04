package com.example.addresssearch.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    val BASE_URL = "https://digi-api.airtel.in"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNetworkService() = getRetrofit().create(ApiClient::class.java)
}