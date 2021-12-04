package com.example.addresssearch.repo

import androidx.lifecycle.LiveData
import com.example.addresssearch.models.ResponseDTO
import com.example.addresssearch.remote.Network
import javax.inject.Inject
import javax.inject.Singleton

class AddressRepo {
    private val apiService = Network.getNetworkService()

    suspend fun getSearchResults(query: String): ResponseDTO {
        return apiService.getSearchResults(query)
    }
}