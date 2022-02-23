package com.example.test_kotlin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Query("Select emailAddress=:emailAddress,password=:password from user_data_tb")
    fun checkUser(emailAddress: String, password: String): Boolean
}