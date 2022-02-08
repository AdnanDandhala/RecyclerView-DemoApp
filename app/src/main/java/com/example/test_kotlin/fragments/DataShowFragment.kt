package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.DataShowAdapter
import com.example.test_kotlin.databinding.FragmentDataShowBinding


class DataShowFragment : Fragment() {
    private lateinit var binding: FragmentDataShowBinding
    private val list = arrayListOf(
        "C",
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
        "Java Script",
        "Android"
    )


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
        binding.tvTittleToolbar.text =
            HtmlCompat.fromHtml(
                "<font color=\"black\">$finalResult</font>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        setAdapter()
        binding.imgBackDataShow.setOnClickListener {
            findNavController().navigate(R.id.action_dataShow2_to_recyclerviewData2)
        }
    }

    private fun setAdapter() {
        val adapter = DataShowAdapter(list)
        binding.RecyclerViewDataShow.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerViewDataShow.adapter = adapter
    }


}