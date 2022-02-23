package com.example.test_kotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_tb")
data class Users(
    @ColumnInfo(name = "userName")
    var UserName: String,
    @ColumnInfo(name = "mobileNo")
    var MobileNo: String,
    @ColumnInfo(name = "emailAddress")
    var EmailAddress: String,
    @ColumnInfo(name = "password")
    var Password: String,
    @ColumnInfo(name = "address")
    var Address: String,
    @ColumnInfo(name = "pincode")
    var Pincode: String,
    @ColumnInfo(name = "city")
    var City: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
