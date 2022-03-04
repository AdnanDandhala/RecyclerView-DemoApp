package com.example.test_kotlin.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<Users>> = userDao.getDetails()

    suspend fun addUser(users: Users) {
        userDao.insertUser(users)
    }

    fun checkEmail(emailAddress: String): Boolean {
        return userDao.checkEmail(emailAddress)
    }

    fun checkUser(emailAddress: String, password: String): Boolean {
        return userDao.checkUser(emailAddress, password)
    }

    fun getDetails(): LiveData<List<Users>> {
        return userDao.getDetails()
    }

    fun getRequested(ID: Int): LiveData<Users> {
        return userDao.getRequested(ID)
    }

    fun updateData(users: Users) {
        CoroutineScope(IO).launch {
            userDao.updateData(users)
        }
    }

    fun deleteUser(users: Users) {
        CoroutineScope(IO).launch {
            userDao.deleteUser(users)
        }
    }
}
