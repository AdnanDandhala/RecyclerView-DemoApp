package com.example.test_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.TabViewPagerAdapter
import com.example.test_kotlin.databinding.FragmentDemo3Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
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

@Suppress("DEPRECATION")
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
        binding.demo3Toolbar.title = "Login"
        val adapter = TabViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.demo3ViewPager.adapter = adapter
        TabLayoutMediator(binding.demo3TabLayout, binding.demo3ViewPager) { tab, position ->
            tab.text = TabNames[position]
        }.attach()
        binding.demo3TabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        if (!binding.demo3Toolbar.isVisible) {
                            binding.demo3Toolbar.visibility = View.VISIBLE
                        }
                        binding.demo3Toolbar.title = "Login"
                    }
                    1 -> {
                        if (!binding.demo3Toolbar.isVisible) {
                            binding.demo3Toolbar.visibility = View.VISIBLE
                        }
                        binding.demo3Toolbar.title = "Register"
                    }
                    2 -> {
                        binding.demo3Toolbar.visibility = View.GONE
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.demo3ViewPager.isUserInputEnabled = false
        setTabIcon()
    }


    private fun setTabIcon() {
        binding.demo3TabLayout.getTabAt(0)?.setIcon(tabIcons[0])
        binding.demo3TabLayout.getTabAt(1)?.setIcon(tabIcons[1])
        binding.demo3TabLayout.getTabAt(2)?.setIcon(tabIcons[2])
    }

}