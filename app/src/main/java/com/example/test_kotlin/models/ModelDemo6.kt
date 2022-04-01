package com.example.test_kotlin.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class ModelDemo6(
    val tittle: String = "",
    @ServerTimestamp
    val date: Date? = null,
    val day: String = ""
)