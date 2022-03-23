package com.example.test_kotlin.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.*
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
        val databaseReference: DatabaseReference = dataBase.reference
        val childRef = databaseReference.child("Time").child("TEST")
        val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        Log.e("TIME", "The Current Date And Time Is $currentTime")
        childRef.setValue(currentTime)

        childRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.key
                if (value?.equals("Time") == true) {
                    val data = snapshot.getValue(String::class.java)
                    Log.d("TIME", "Value Is $data")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TIME", "Failed to Read Value.", error.toException())
            }
        })
    }
}