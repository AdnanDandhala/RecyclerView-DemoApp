package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.test_kotlin.databinding.FragmentDemo5Binding
import com.example.test_kotlin.worker.MyWorker
import java.util.concurrent.TimeUnit


class FragmentDemo5 : Fragment() {
    private lateinit var binding: FragmentDemo5Binding
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
        val myWorker = WorkManager.getInstance(requireContext())
        val workRequest =
            PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).build()
        myWorker.enqueue(workRequest)
    }
}