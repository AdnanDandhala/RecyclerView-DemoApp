package com.example.test_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.databinding.FragmentRecyclerviewDataBinding


class RecyclerviewData : Fragment() {
    private lateinit var binding: FragmentRecyclerviewDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewDataBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
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