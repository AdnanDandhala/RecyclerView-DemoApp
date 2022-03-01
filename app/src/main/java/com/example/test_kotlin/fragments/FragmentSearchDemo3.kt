package com.example.test_kotlin.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.adapters.SearchDemo3Adapter
import com.example.test_kotlin.databinding.FragmentSearchDemo3Binding
import com.example.test_kotlin.room.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSearchDemo3 : Fragment() {
    private lateinit var binding: FragmentSearchDemo3Binding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchDemo3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val listUser = userViewModel.getDetails(requireContext())
                val adapter = SearchDemo3Adapter(listUser)
                withContext(Dispatchers.Main) {
                    binding.recyclerViewSearchDemo3.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerViewSearchDemo3.adapter = adapter
                }
            }
        }
    }
}