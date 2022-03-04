package com.example.test_kotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*

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

    @Query("SELECT * FROM user_data_tb WHERE id=:ID")
    fun getRequested(ID: Int): LiveData<Users>

    @Update
    suspend fun updateData(users: Users)

    @Delete
    suspend fun deleteUser(users: Users)
}
