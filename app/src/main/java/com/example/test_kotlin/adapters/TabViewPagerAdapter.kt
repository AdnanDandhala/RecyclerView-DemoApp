package com.example.test_kotlin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test_kotlin.fragments.FragmentLoginDemo3
import com.example.test_kotlin.fragments.FragmentRegisterDemo3
import com.example.test_kotlin.fragments.FragmentSearchDemo3

private const val NUM_TABS = 3

class TabViewPagerAdapter
    (
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentLoginDemo3()
            1 -> FragmentRegisterDemo3()
            2 -> FragmentSearchDemo3()
            else -> {
                Fragment()
            }
        }
    }
}