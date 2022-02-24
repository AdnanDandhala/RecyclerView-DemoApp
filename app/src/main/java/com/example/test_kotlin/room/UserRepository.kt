package com.example.test_kotlin.room

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {
    companion object {
        private var usersDatabase: UsersDatabase? = null
        private fun initializeDB(context: Context): UsersDatabase {
            return UsersDatabase.getDatabaseObj(context)
        }

        fun insertData(
            context: Context,
            username: String,
            mobileNo: String,
            emailAddress: String,
            password: String,
            address: String,
            pinCode: String,
            city: String
        ) {
            usersDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val userDetails =
                    Users(username, mobileNo, emailAddress, password, address, pinCode, city)
                usersDatabase!!.userDao().insertUser(userDetails)
            }
        }

        fun checkEmail(context: Context, emailAddress: String): Boolean {
            usersDatabase = initializeDB(context)
            return usersDatabase!!.userDao().checkEmail(emailAddress)
        }

        fun checkUser(context: Context, emailAddress: String, password: String): Boolean {
            usersDatabase = initializeDB(context)
            return usersDatabase!!.userDao().checkUser(emailAddress, password)
        }
    }
}