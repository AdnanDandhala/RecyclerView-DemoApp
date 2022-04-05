package com.example.test_kotlin.models

import java.util.*

data class FirestoreModelItems(
    val date: Date,
    val tittle: String,
    val day: String,
    val userId: Int
) {
    fun convertDate(): String {
        return date.toString()
    }
}
