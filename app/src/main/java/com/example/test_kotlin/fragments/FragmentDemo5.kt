package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.test_kotlin.FirebaseHelper
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo5Adapter
import com.example.test_kotlin.databinding.FragmentDemo5Binding
import com.example.test_kotlin.viewmodel.MainViewModel
import com.example.test_kotlin.worker.MyWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
@SuppressLint("NotifyDataSetChanged")
class FragmentDemo5 : Fragment() {
    lateinit var binding: FragmentDemo5Binding
    private lateinit var adapter: Demo5Adapter
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
        binding.toolbarDemo5.setNavigationOnClickListener {
            findNavController().navigate(R.id.fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo5, true)
        }
        val myWorker = WorkManager.getInstance(requireContext())
        val workRequest =
            PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).build()
        myWorker.enqueue(workRequest)
        binding.pbLoadDataDemo5.visibility = View.VISIBLE
        binding.tvProgressTextDemo5.visibility = View.VISIBLE
        binding.recyclerViewDemo5.visibility = View.GONE
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getDataFirebase().observe(requireActivity()) {
            if (it.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    adapter = Demo5Adapter(it)
                    withContext(Dispatchers.Main) {
                        binding.recyclerViewDemo5.isNestedScrollingEnabled = false
                        binding.recyclerViewDemo5.setItemViewCacheSize(100)
                        binding.recyclerViewDemo5.adapter = adapter
                        binding.recyclerViewDemo5.layoutManager =
                            LinearLayoutManager(context)
                        binding.pbLoadDataDemo5.visibility = View.GONE
                        binding.tvProgressTextDemo5.visibility = View.GONE
                        binding.recyclerViewDemo5.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}