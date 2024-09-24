package com.example.nc_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NCMainFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topMainTabLayout: TabLayout = view.findViewById(R.id.nc_top_main_tab_layout)
        val topMainViewPager: ViewPager2 = view.findViewById(R.id.nc_top_main_view_pager)
        val mainTopTabNavigation = MainTopTabNavigationAdapter(this@NCMainFragmentActivity)

        topMainViewPager.adapter = mainTopTabNavigation

        TabLayoutMediator(topMainTabLayout, topMainViewPager) {
            tab, position ->
            tab.text = when(position) {
                0 -> "LESSONS"
                else -> "NC+"
            }
        }.attach()
    }
}