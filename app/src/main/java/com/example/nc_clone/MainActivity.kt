package com.example.nc_clone

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NCFragmentRatingsOrder.OnTeacherFragmentClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nc_main_view_frame, NCMainFragmentActivity())
                .commit()
        }
    }

    override fun onContainerClicked(teacherDetail: TeacherDetail) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nc_main_view_frame, NCTeacherDetail(teacherDetail, this@MainActivity, R.id.nc_main_view_frame))
            .addToBackStack(null)
            .commit()
    }
}