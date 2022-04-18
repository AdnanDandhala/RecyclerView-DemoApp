package com.example.test_kotlin.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

data class FirestoreModelItems(
    val date: Long = 0,
    val tittle: String = "",
    val day: String = "",
) {
    @SuppressLint("SimpleDateFormat")
    fun setDate(): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("d-mm-yyyy")
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return formatter.format(calendar.time)
    }
}
