package com.example.nc_clone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
class NCFragmentActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topNcTabLayout: TabLayout = view.findViewById(R.id.nc_tab_layout)
        val topNcViewPager: ViewPager2 = view.findViewById(R.id.nc_view_pager)
        val ncTopTabNavigation = NCTopTabNavigationAdapter(this@NCFragmentActivity)

        topNcViewPager.adapter = ncTopTabNavigation

        TabLayoutMediator(topNcTabLayout, topNcViewPager) {
                tab, position ->
            tab.text = when(position) {
                0 -> "RATINGS ORDER"
                1 -> "KIDS RATINGS ORDER"
                2 -> "LESSON COUNT ORDER"
                3 -> "FAVORITE COUNT ORDER"
                4 -> "TUTOR HISTORY ORDER"
                else -> "NATIVE ORDER"
            }
        }.attach()
    }
}

