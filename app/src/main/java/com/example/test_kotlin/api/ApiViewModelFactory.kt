package com.example.test_kotlin.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApiViewModelFactory constructor(private val repository: ApiRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            ApiViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}