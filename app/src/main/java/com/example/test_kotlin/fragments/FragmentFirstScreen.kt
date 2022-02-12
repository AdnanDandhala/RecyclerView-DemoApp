package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.FragmentFirstScreenBinding

class FragmentFirstScreen : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDemo1.setOnClickListener(this)
        binding.btnDemo2.setOnClickListener(this)
        binding.btnDemo3.setOnClickListener(this)
        binding.btnDemo4.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnDemo1.id -> {
                findNavController().navigate(R.id.frontDataFragment)
            }
            binding.btnDemo2.id -> {
                findNavController().navigate(R.id.fragmentDemo2)
            }
            binding.btnDemo3.id -> {
                Toast.makeText(requireContext(), "Demo 3 Was Clicked", Toast.LENGTH_SHORT).show()
            }
            binding.btnDemo4.id -> {
                Toast.makeText(requireContext(), "Demo 4 Was Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}