package com.example.addresssearch.repo

import androidx.lifecycle.LiveData
import com.example.addresssearch.models.ResponseDTO
import com.example.addresssearch.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

class AddressRepo @Inject constructor(val api:ApiClient) {
//    private val apiService = Network.getNetworkService()

    suspend fun getSearchResults(query: String): ResponseDTO {
        return api.getSearchResults(query)
    }
}