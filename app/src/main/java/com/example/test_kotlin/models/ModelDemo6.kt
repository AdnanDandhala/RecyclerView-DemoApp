package com.example.test_kotlin.models

data class ModelDemo6(
    val born: Int = 0,
    val first: String = "",
    val last: String = "",
    val middle: String = "",
    val time: String = ""
) {
    fun convertBornToString(): String {
        return born.toString()
    }
}