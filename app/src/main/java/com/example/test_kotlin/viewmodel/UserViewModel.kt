package com.example.test_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.test_kotlin.room.UserRepository
import com.example.test_kotlin.room.Users
import com.example.test_kotlin.room.UsersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Users>>
    private val repository: UserRepository

    init {
        val userDao = UsersDatabase.getDatabaseObj(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(users)
        }

    }

    fun checkEmail(emailAddress: String): Boolean {
        return repository.checkEmail(emailAddress)
    }

    fun checkUser(
        emailAddress: String,
        password: String
    ): Boolean {
        return repository.checkUser(emailAddress, password)
    }

    fun getDetails(): LiveData<List<Users>> {
        return repository.getDetails()
    }

    fun getRequested(ID: Int): LiveData<Users> {
        return repository.getRequested(ID)
    }

    fun updateData(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(users)
        }
    }

    fun deleteUser(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(users)
        }
    }

}