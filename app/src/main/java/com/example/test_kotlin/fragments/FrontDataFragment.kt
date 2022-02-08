package com.example.test_kotlin.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.MainViewModel
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.LayoutHolderAdapter
import com.example.test_kotlin.databinding.FragmentRecyclerviewDataBinding


class FrontDataFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerviewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    override fun onStart() {
        super.onStart()
        findNavController().popBackStack(R.id.dataShow2, true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "Constraint-Task"
        if ((activity as AppCompatActivity).supportActionBar?.isShowing != true) {
            (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

    private fun setData() {
        Log.i("TAG", "SetData Called")
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.setDataUsingViewModel().observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = LayoutHolderAdapter(requireContext(), it)
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.mainRecyclerView.visibility = View.VISIBLE
                binding.mainRecyclerView.adapter = adapter
            } else {
                Toast.makeText(requireContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.setDataUsingViewModel()
    }
}