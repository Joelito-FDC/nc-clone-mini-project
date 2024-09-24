package com.example.nc_clone

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NCTopTabNavigationAdapter(fragmentActivity: NCFragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NCFragmentRatingsOrder()
            1 -> NCFragmentRatingsOrder()
            2 -> NCFragmentRatingsOrder()
            3 -> NCFragmentRatingsOrder()
            4 -> NCFragmentRatingsOrder()
            else -> NCPlusFragmentActivity()
        }
    }
}