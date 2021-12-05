package com.example.addresssearch.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.addresssearch.models.ResponseDTO
import com.example.addresssearch.repo.AddressRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repo: AddressRepo) : ViewModel() {

    suspend fun getSearchResults(query: String): ResponseDTO {
        return repo.getSearchResults(query)
    }
}