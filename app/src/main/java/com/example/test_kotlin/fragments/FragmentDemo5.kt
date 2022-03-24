package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.test_kotlin.FirebaseHelper
import com.example.test_kotlin.adapters.Demo5Adapter
import com.example.test_kotlin.databinding.FragmentDemo5Binding
import com.example.test_kotlin.worker.MyWorker
import com.google.firebase.database.*
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
@SuppressLint("NotifyDataSetChanged")
class FragmentDemo5 : Fragment() {
    lateinit var binding: FragmentDemo5Binding
    val list = ArrayList<FirebaseHelper>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo5Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbLoadDataDemo5.visibility = View.VISIBLE
        binding.tvTextPbLoading.visibility = View.VISIBLE
        binding.recyclerViewDemo5.visibility = View.GONE
        /* if (list.isNotEmpty()) {
             binding.recyclerViewDemo5.visibility = View.VISIBLE
             binding.pbLoadDataDemo5.visibility = View.GONE
             binding.tvTextPbLoading.visibility = View.GONE
         } else {
             binding.recyclerViewDemo5.visibility = View.GONE
             binding.pbLoadDataDemo5.visibility = View.VISIBLE
             binding.tvTextPbLoading.visibility = View.VISIBLE
             uploadTime()
         }*/
        uploadTime()
        val myWorker = WorkManager.getInstance(requireContext())
        val workRequest =
            PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).build()
        myWorker.enqueue(workRequest)

    }

    private fun uploadTime() {
        binding.pbLoadDataDemo5.visibility = View.GONE
        binding.tvTextPbLoading.visibility = View.GONE
        binding.recyclerViewDemo5.visibility = View.VISIBLE
        val adapter = Demo5Adapter(list)
        binding.recyclerViewDemo5.isNestedScrollingEnabled = false
        binding.recyclerViewDemo5.setItemViewCacheSize(100)
        binding.recyclerViewDemo5.adapter = adapter
        binding.recyclerViewDemo5.layoutManager = LinearLayoutManager(requireContext())
        val dataBase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = dataBase.getReference("Date")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val yourDataDate: HashMap<String, Any> =
                        dataSnapShot.value as HashMap<String, Any>
                    Log.i("BEFORE", dataSnapShot.key.toString())
                    list.add(FirebaseHelper(dataSnapShot.key.toString(), yourDataDate.toString()))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}