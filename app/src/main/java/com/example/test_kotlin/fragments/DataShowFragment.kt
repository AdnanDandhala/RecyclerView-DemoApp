package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.FragmentDataShowBinding


class DataShowFragment : Fragment() {
    private lateinit var binding: FragmentDataShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ((activity as AppCompatActivity).supportActionBar?.isShowing != true) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDataShow)
        }
        val bundle = arguments
        val finalResult = bundle?.getString("KEY_1")
        binding.tvTittleToolbar.text = finalResult
        binding.imgBackDataShow.setOnClickListener {
            findNavController().navigate(R.id.action_dataShow2_to_recyclerviewData2)
        }
        binding.btnSendMessage.setOnClickListener {
            setAdapter()
        }
    }

    private fun setAdapter() {

    }
}