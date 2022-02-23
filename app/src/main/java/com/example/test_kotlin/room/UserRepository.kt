package com.example.test_kotlin.room

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {
    companion object {
        var usersDatabase: UsersDatabase? = null
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
            pincode: String,
            city: String
        ) {
            usersDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val userDetails =
                    Users(username, mobileNo, emailAddress, password, address, pincode, city)
                usersDatabase!!.userDao().insertUser(userDetails)
            }
        }
    }
}