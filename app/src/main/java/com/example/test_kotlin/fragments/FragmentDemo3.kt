package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test_kotlin.databinding.FragmentDemo3Binding

class FragmentDemo3 : Fragment() {
    private lateinit var binding: FragmentDemo3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo3Binding.inflate(layoutInflater)
        return binding.root
    }
}