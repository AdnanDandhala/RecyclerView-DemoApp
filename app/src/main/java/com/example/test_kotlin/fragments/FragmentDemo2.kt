package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo2Adapter
import com.example.test_kotlin.databinding.FragmentDemo2Binding
import com.example.test_kotlin.models.ModelDemo2


class FragmentDemo2 : Fragment(), Demo2Adapter.CalculateTotal {
    private lateinit var binding: FragmentDemo2Binding
    private val aList = ArrayList<ModelDemo2>()

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
        binding.demo2Toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentDemo22_to_fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo2, true)
        }
        setAdapter()
    }

    private fun setAdapter() {
        aList.add(ModelDemo2("1"))
        aList.add(ModelDemo2("2"))
        aList.add(ModelDemo2("3"))
        aList.add(ModelDemo2("4"))
        aList.add(ModelDemo2("5"))
        aList.add(ModelDemo2("6"))
        aList.add(ModelDemo2("7"))
        aList.add(ModelDemo2("8"))
        aList.add(ModelDemo2("9"))
        aList.add(ModelDemo2("10"))
        val adapter = Demo2Adapter(aList, this)
        binding.recyclerViewDemo2.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDemo2.adapter = adapter
    }

    override fun setTotal(total: Int) {
        binding.tvDemo2Total.text = total.toString()
    }
}