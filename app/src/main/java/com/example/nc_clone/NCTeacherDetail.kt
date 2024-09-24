package com.example.nc_clone

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NCTeacherDetail(private val teacherDetail: TeacherDetail, private val mainActivity: MainActivity, private val mainViewFrame: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_teacher_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teacherBackButton: ImageButton = view.findViewById(R.id.nc_teacher_back_button)
        val teacherYoutubeVidView: ImageView = view.findViewById(R.id.nc_teacher_youtube_vid)
        val teacherNameTextView: TextView = view.findViewById(R.id.nc_teacher_name)
        val teacherAgeTextView: TextView = view.findViewById(R.id.nc_teacher_age)
        val teacherProfileView: ImageView = view.findViewById(R.id.nc_teacher_image)
        val teacherNationalityFlagView: ImageView = view.findViewById(R.id.nc_teacher_nationality_view)
        val teacherNationalityView: TextView = view.findViewById(R.id.nc_teacher_nationality_name)
        val teacherFavoriteCount: TextView = view.findViewById(R.id.nc_teacher_favorite_count)
        val teacherLessonCountView: TextView = view.findViewById(R.id.nc_teacher_monitor_rating)
        val teacherKidsRatingView: TextView = view.findViewById(R.id.nc_teacher_kids_rating)
        val teacherStarRating: TextView = view.findViewById(R.id.nc_teacher_star_rating)

        teacherBackButton.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction()
                .replace(mainViewFrame, NCMainFragmentActivity())
                .addToBackStack(null)
                .commit()
        }

        Glide.with(requireContext())
            .load(teacherDetail.imageProfile)
            .into(teacherProfileView)

        teacherNameTextView.text = teacherDetail.name
        teacherAgeTextView.text = "(Age: ${teacherDetail.age})"

        Glide.with(requireContext())
            .load(teacherDetail.countryImage)
            .into(teacherNationalityFlagView)

        teacherNationalityView.text = teacherDetail.country
        teacherFavoriteCount.text = "${teacherDetail.favoriteCount} people"
        teacherLessonCountView.text = "${teacherDetail.lesson} times"
        teacherKidsRatingView.text = teacherDetail.kidsRating.toString()
        teacherStarRating.text = teacherDetail.rating.toString()

        Glide.with(requireContext())
            .load(teacherDetail.imageProfile)
            .into(teacherYoutubeVidView)

        val ncTeacherDetailTabLayout: TabLayout = view.findViewById(R.id.nc_teacher_detail_tab_layout)
        val ncTeacherDetailViewPager: ViewPager2 = view.findViewById(R.id.nc_teacher_detail_view_pager)
        val ncTeacherTopTabNavigation = NCTeacherDetailNavigationAdapter(this@NCTeacherDetail, teacherDetail)

        ncTeacherDetailViewPager.adapter = ncTeacherTopTabNavigation

        TabLayoutMediator(ncTeacherDetailTabLayout, ncTeacherDetailViewPager) {
            tab, position ->
            tab.text = when(position) {
                0 -> "TUTOR'S PROFILE"
                1 -> "LESSON DETAILS"
                else -> "REVIEW"
            }
        }.attach()
    }
}