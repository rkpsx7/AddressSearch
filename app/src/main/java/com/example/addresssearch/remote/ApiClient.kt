package com.example.addresssearch.remote

import androidx.lifecycle.LiveData
import com.example.addresssearch.models.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("compassLocation/rest/address/autocomplete?")
    suspend fun getSearchResults(
        @Query("queryString") query:String
    ):ResponseDTO


}