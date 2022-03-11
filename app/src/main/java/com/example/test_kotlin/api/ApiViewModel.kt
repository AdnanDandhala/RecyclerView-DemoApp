package com.example.test_kotlin.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel constructor(private val repository: ApiRepository) : ViewModel() {
    val usersList = MutableLiveData<ArrayList<UsersItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers() {
        val response = repository.getUsers()
        response.enqueue(object : Callback<ArrayList<UsersItem>?> {
            override fun onResponse(
                call: Call<ArrayList<UsersItem>?>,
                response: Response<ArrayList<UsersItem>?>
            ) {
                usersList.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<UsersItem>?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}