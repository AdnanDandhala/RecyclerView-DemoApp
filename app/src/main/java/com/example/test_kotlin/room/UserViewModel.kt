package com.example.test_kotlin.room

import android.content.Context
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    fun insertData(
        context: Context, username: String,
        mobileNo: String,
        emailAddress: String,
        password: String,
        address: String,
        pincode: String,
        city: String
    ) {
        UserRepository.insertData(
            context,
            username,
            mobileNo,
            emailAddress,
            password,
            address,
            pincode,
            city
        )
    }

    fun checkEmail(context: Context, emailAddress: String): Boolean {
        return UserRepository.checkEmail(context, emailAddress)
    }

    fun checkUser(
        context: Context,
        emailAddress: String,
        password: String
    ): Boolean {
        return UserRepository.checkUser(context, emailAddress, password)
    }
}