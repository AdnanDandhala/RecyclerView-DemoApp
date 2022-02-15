package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo2Adapter
import com.example.test_kotlin.databinding.FragmentDemo2Binding
import com.example.test_kotlin.viewmodel.MainViewModel


class FragmentDemo2 : Fragment(), Demo2Adapter.CalculateTotal {
    private lateinit var binding: FragmentDemo2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ((activity as AppCompatActivity).supportActionBar?.isShowing != true) {
            (activity as AppCompatActivity).setSupportActionBar(binding.demo2Toolbar)
        }
        binding.demo2Toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDemo22_to_fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo2, true)
        }
        setAdapter()
    }

    private fun setAdapter() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.setDataDemo2UsingViewModel().observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = Demo2Adapter(it, this)
                binding.recyclerViewDemo2.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewDemo2.adapter = adapter
            } else {
                Toast.makeText(
                    requireContext(),
                    "Some Error Occurred In Demo2 Fragment",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.setDataDemo2UsingViewModel()
    }

    override fun setTotal(total: Int) {
        binding.tvDemo2Total.text = total.toString()
    }


}