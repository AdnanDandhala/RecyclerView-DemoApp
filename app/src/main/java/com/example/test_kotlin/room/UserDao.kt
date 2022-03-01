package com.example.test_kotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Query("SELECT count(emailAddress) From user_data_tb WHERE emailAddress =:emailAddress")
    fun checkEmail(emailAddress: String): Boolean

    @Query("SELECT count(emailAddress) FROM user_data_tb WHERE emailAddress=:emailAddress AND password=:password")
    fun checkUser(emailAddress: String, password: String): Boolean

    @Query("SELECT * FROM user_data_tb")
    fun getDetails(): LiveData<List<Users>>
}
