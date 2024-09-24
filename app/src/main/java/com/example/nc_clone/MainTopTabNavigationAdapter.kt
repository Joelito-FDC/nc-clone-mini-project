package com.example.nc_clone

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainTopTabNavigationAdapter(fragmentActivity: NCMainFragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NCFragmentActivity()
            else -> NCPlusFragmentActivity()
        }
    }
}