package com.example.test_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.databinding.FragmentRecyclerviewDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecyclerviewData : Fragment() {
    private lateinit var binding: FragmentRecyclerviewDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewDataBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repeat(1) {
                    setData()
                }
            }
        }
        return binding.root
    }

    private fun setData() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.add().observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = LayoutHolderAdapter(requireContext(), it)
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.mainRecyclerView.visibility = View.VISIBLE
                binding.mainRecyclerView.adapter = adapter
            } else {
                Toast.makeText(requireContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
        repeat(10) {
            viewModel.setDataUsingViewModel()
        }
    }
}