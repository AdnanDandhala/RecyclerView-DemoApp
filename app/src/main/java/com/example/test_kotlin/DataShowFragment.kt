package com.example.test_kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_kotlin.databinding.FragmentDataShowBinding


class DataShowFragment : Fragment() {
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
        "Java Script",
        "Android"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                findNavController().navigate(R.id.action_dataShow2_to_recyclerviewData2)
        }
        return true
    }


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
        val bundle = arguments
        val finalResult = bundle?.getString("KEY_1")
        (activity as AppCompatActivity).supportActionBar?.title = finalResult
        Log.i("TAG", bundle.toString())
        setAdapter()
        binding.ListViewDataShow.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(context, "$i", Toast.LENGTH_SHORT).show()
        }
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