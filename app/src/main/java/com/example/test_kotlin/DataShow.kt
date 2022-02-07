package com.example.test_kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.test_kotlin.databinding.FragmentDataShowBinding


class DataShow : Fragment() {
    private lateinit var binding: FragmentDataShowBinding
    private val listOfDummyText = mutableListOf(
        "C ",
        "C++",
        "Java",
        ".Net",
        "Kotlin",
        "Ruby",
        "Rails",
        "Python",
        "Java Script",
        "Php",
        "Ajax",
        "Perl",
        "Hadoop",
        ".Net",
        "Kotlin",
        "Ruby",
        "Rails",
        "Python",
        "Java Script"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataShowBinding.inflate(layoutInflater)
        val bundle = arguments
        val resultText = bundle?.getString("KEY_1")
        Log.i("TAG", bundle.toString())
        binding.tvDataShow.text = resultText
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        val arrayAdapter =
            ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_list_item_1,
                listOfDummyText
            )
        binding.ListViewDataShow.adapter = arrayAdapter
    }

}