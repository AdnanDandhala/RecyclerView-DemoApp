package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.adapters.Demo4Adapter
import com.example.test_kotlin.api.ApiRepository
import com.example.test_kotlin.api.ApiViewModel
import com.example.test_kotlin.api.ApiViewModelFactory
import com.example.test_kotlin.api.UserApiInterface
import com.example.test_kotlin.databinding.FragmentDemo4Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class FragmentDemo4 : Fragment() {
    private lateinit var binding: FragmentDemo4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo4Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            ApiViewModelFactory(ApiRepository(UserApiInterface.getInstance()))
        )[ApiViewModel::class.java]
        viewModel.getAllUsers()
        viewModel.usersList.observe(requireActivity()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val adapter = Demo4Adapter(it)
                withContext(Dispatchers.Main) {
                    binding.mainRecyclerViewDemo4.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.mainRecyclerViewDemo4.adapter = adapter
                }
            }
        }
    }
}