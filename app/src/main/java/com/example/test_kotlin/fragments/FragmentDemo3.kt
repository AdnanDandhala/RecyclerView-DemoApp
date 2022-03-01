package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.TabViewPagerAdapter
import com.example.test_kotlin.databinding.FragmentDemo3Binding
import com.google.android.material.tabs.TabLayoutMediator

val TabNames = arrayOf(
    "Login",
    "Register",
    "Search"
)
val tabIcons = arrayOf(
    R.drawable.ic_baseline_login_24,
    R.drawable.ic_baseline_register_24,
    R.drawable.ic_baseline_search_24
)


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.demo3ViewPager.adapter = adapter
        TabLayoutMediator(binding.demo3TabLayout, binding.demo3ViewPager) { tab, position ->
            tab.text = TabNames[position]
        }.attach()
        setTabIcon()
    }


    private fun setTabIcon() {
        binding.demo3TabLayout.getTabAt(0)?.setIcon(tabIcons[0])
        binding.demo3TabLayout.getTabAt(1)?.setIcon(tabIcons[1])
        binding.demo3TabLayout.getTabAt(2)?.setIcon(tabIcons[2])
    }

}