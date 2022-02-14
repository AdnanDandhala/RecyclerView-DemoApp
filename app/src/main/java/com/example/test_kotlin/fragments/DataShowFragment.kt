package com.example.test_kotlin.fragments

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.DataShowAdapter
import com.example.test_kotlin.databinding.FragmentDataShowBinding
import com.example.test_kotlin.models.ModelDataShow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DataShowFragment : Fragment() {
    private lateinit var binding: FragmentDataShowBinding
    private val finalList = ArrayList<ModelDataShow>()

    private val adapter = DataShowAdapter(finalList)
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
        binding.etSendMessage.hint = "Message $finalResult"
        setDefaultAdapter(finalResult)
        binding.toolbarDataShow.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_dataShowFragment_to_frontDataFragment)
        }
        binding.imgSendMessage.setOnClickListener {
            setAdapter()
        }
    }

    private fun setDefaultAdapter(name: String?) {
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val tempTime = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
        val answer: String = current.format(tempTime)
        binding.tvTime.text = answer
        val message = "Hello $name"
        finalList.add(
            ModelDataShow(
                DataShowAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                message_receiver = message.replace("\n", ""),
                R.drawable.woman,
                message_sender = message.replace("\n", "")
            )
        )
        finalList.add(
            ModelDataShow(
                DataShowAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                message_receiver = message.replace("\n", ""),
                R.drawable.woman,
                message_sender = message.replace("\n", "")
            )
        )
        finalList.add(
            ModelDataShow(
                DataShowAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                message_receiver = message.replace("\n", ""),
                R.drawable.woman,
                message_sender = message.replace("\n", "")
            )
        )
        finalList.add(
            ModelDataShow(
                2,
                R.drawable.man1,
                message_receiver = message.replace("\n", ""),
                R.drawable.woman,
                message_sender = message.replace("\n", "")
            )
        )
        finalList.add(
            ModelDataShow(
                2,
                R.drawable.man1,
                message_receiver = message.replace("\n", ""),
                R.drawable.woman,
                message_sender = message.replace("\n", "")
            )
        )
        binding.etSendMessage.text?.clear()
        adapter.notifyItemInserted(finalList.size - 1)
        binding.recyclerViewDataShow.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDataShow.adapter = adapter
    }

    private fun setAdapter() {
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val tempTime = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
        val answer: String = current.format(tempTime)
        val message: String
        if (!TextUtils.isEmpty(binding.etSendMessage.text)) {
            binding.etSendMessage.defaultFocusHighlightEnabled = false
            binding.tvTime.text = answer
            message = binding.etSendMessage.text.toString()
            val adjusted = message.replaceRange(0, message.length, message)
            finalList.add(
                ModelDataShow(
                    2,
                    R.drawable.man1,
                    message_receiver = adjusted.replace("\n", ""),
                    R.drawable.woman,
                    message_sender = adjusted.replace("\n", "")
                )
            )
            binding.etSendMessage.text?.clear()
            adapter.notifyItemInserted(finalList.size - 1)
            binding.recyclerViewDataShow.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewDataShow.adapter = adapter
            binding.recyclerViewDataShow.scrollToPosition(finalList.size - 1)
        } else {
            Toast.makeText(requireContext(), "Enter Message", Toast.LENGTH_SHORT).show()
        }
    }

}