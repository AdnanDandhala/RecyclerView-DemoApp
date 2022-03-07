package com.example.test_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.LayoutHolderAdapter
import com.example.test_kotlin.models.ModelDemo2
import com.example.test_kotlin.models.ModelLayoutHolder
import com.example.test_kotlin.room.UserRepository
import com.example.test_kotlin.room.Users
import com.example.test_kotlin.room.UsersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var liveData: MutableLiveData<ArrayList<ModelLayoutHolder>> = MutableLiveData()
    private var demo2LiveData: MutableLiveData<ArrayList<ModelDemo2>> = MutableLiveData()
    private val readAllData: LiveData<List<Users>>
    private val repository: UserRepository

    init {
        val userDao = UsersDatabase.getDatabaseObj(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun setDataDemo2UsingViewModel(): MutableLiveData<ArrayList<ModelDemo2>> {
        val dataList = ArrayList<ModelDemo2>()
        dataList.add(
            ModelDemo2("1")
        )
        dataList.add(
            ModelDemo2("2")
        )
        dataList.add(
            ModelDemo2("3")
        )
        dataList.add(
            ModelDemo2("4")
        )
        dataList.add(
            ModelDemo2("5")
        )
        dataList.add(
            ModelDemo2("6")
        )
        dataList.add(
            ModelDemo2("7")
        )
        demo2LiveData.value = dataList
        return demo2LiveData
    }

    fun setDataUsingViewModel(): MutableLiveData<ArrayList<ModelLayoutHolder>> {
        val dataList = ArrayList<ModelLayoutHolder>()
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.businessman1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.businessman1,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.businessman1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.man1,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.person,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.person,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.person,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.man1,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        liveData.value = dataList
        return liveData
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