package com.example.nc_clone

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NCTeacherDetailNavigationAdapter(ncTeacherDetail: NCTeacherDetail, private val teacherDetail: TeacherDetail) : FragmentStateAdapter(ncTeacherDetail) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NCTeacherDetailTutorsProfile(teacherDetail)
            1 -> NCTeacherDetailTutorsProfile(teacherDetail)
            else -> NCTeacherDetailTutorsProfile(teacherDetail)
        }
    }
}