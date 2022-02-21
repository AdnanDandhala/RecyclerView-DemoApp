package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.test_kotlin.databinding.FragmentRegistorDemo3Binding

val spinnerItems = arrayOf(
    "Ahmedabad",
    "Rajkot",
    "Mumbai",
    "Bihar",
    "America",
    "London"
)

class FragmentRegisterDemo3 : Fragment() {
    private lateinit var binding: FragmentRegistorDemo3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistorDemo3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1,
            spinnerItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cityDropDown.adapter = adapter
    }
}