package com.example.test_kotlin.api

import com.example.test_kotlin.fragments.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiInterface {
    @GET("users")
    fun getUsers(): Call<ArrayList<UsersItem>>

    companion object {
        var userApiInterface: UserApiInterface? = null

        fun getInstance(): UserApiInterface {
            if (userApiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                userApiInterface = retrofit.create(UserApiInterface::class.java)
            }
            return userApiInterface!!
        }
    }
}