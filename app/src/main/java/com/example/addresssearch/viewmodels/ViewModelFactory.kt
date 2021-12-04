package com.example.addresssearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.addresssearch.repo.AddressRepo

class ViewModelFactory(private val repo: AddressRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(repo) as T
    }
}