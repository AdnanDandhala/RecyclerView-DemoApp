package com.example.test_kotlin.api

class ApiRepository constructor(private val userApiInterface: UserApiInterface) {
    fun getUsers() = userApiInterface.getUsers()
}