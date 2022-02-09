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
import java.time.format.FormatStyle


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
            setAdapter(finalResult.toString())
        }
    }

    private fun setAdapter(name: String) {
        val finalList = ArrayList<ModelDataShow>()
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val time = current.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
        val adapter: DataShowAdapter
        val message: String
        if (!TextUtils.isEmpty(binding.etSendMessage.text)) {
            message = binding.etSendMessage.text.toString()
            finalList.add(
                ModelDataShow(
                    name,
                    message,
                    time,
                    "Adnan",
                    "Hello From Adnan\n Its Me Adnan Here "
                )
            )
            adapter = DataShowAdapter(finalList)
            adapter.notifyItemInserted(finalList.size - 1)
            binding.RecyclerViewDataShow.layoutManager = LinearLayoutManager(requireContext())
            binding.RecyclerViewDataShow.adapter = adapter
        } else {
            Toast.makeText(requireContext(), "Enter Message", Toast.LENGTH_SHORT).show()
        }
    }
}