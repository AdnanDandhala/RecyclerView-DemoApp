package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.FragmentDemo2Binding


class FragmentDemo2 : Fragment() {
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
        binding.imgBackMainScreen.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentDemo22_to_fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo2, true)
        }
    }
}