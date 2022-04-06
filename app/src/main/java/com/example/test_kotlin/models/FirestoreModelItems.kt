package com.example.test_kotlin.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class FirestoreModelItems(
    @ServerTimestamp
    val date: Date? = null,
    val tittle: String = "",
    val day: String = "",
    val userId: Int = 0
) {
    fun convertDate(): String {
        return date.toString()
    }

    fun convertId(): String {
        return userId.toString()
    }
}
