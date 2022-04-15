package com.example.test_kotlin.models

data class FirestoreModelItems(
    val date: Long,
    val tittle: String = "",
    val day: String = "",
) {
    fun convertDate(): String {
        return date.toString()
    }
}
