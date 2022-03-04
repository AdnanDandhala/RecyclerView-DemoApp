package com.example.test_kotlin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test_kotlin.room.UserRepository
import com.example.test_kotlin.room.Users

class UserViewModel : ViewModel() {

    fun insertData(
        context: Context, username: String,
        mobileNo: String,
        emailAddress: String,
        password: String,
        address: String,
        pinCode: String,
        city: String
    ) {
        UserRepository.insertData(
            context,
            username,
            mobileNo,
            emailAddress,
            password,
            address,
            pinCode,
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

    fun getDetails(context: Context): LiveData<List<Users>> {
        return UserRepository.getDetails(context)
    }

    fun getRequested(context: Context, ID: Int): LiveData<Users> {
        return UserRepository.getRequested(context, ID)
    }

    fun updateData(context: Context, users: Users) {

        UserRepository.updateData(context, users)
    }

    fun deleteUser(context: Context, users: Users) {
        UserRepository.deleteUser(context, users)
    }

}