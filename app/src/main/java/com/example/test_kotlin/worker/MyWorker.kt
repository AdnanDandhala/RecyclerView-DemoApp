package com.example.test_kotlin.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.test_kotlin.FirebaseHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        uploadTime()
        return Result.success()
    }

    private fun uploadTime() {
        val dataBase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = dataBase.getReference("Date")
        val currentTime: String =
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val childRef = databaseReference.child(currentTime)
        val helpers = FirebaseHelper(" $currentDate",currentTime)
        childRef.setValue(helpers)
    }
}