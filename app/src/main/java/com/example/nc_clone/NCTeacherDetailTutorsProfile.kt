package com.example.nc_clone

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout

class NCTeacherDetailTutorsProfile(private val teacherDetail: TeacherDetail): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_teacher_detail_tutors_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ncTeacherIntroduction: TextView = view.findViewById(R.id.nc_teacher_introduction)
        val ncTeacherIntroductionTranslate: TextView = view.findViewById(R.id.nc_teacher_introduction_translate)
        val ncTeacherClickableShowTranslation: LinearLayout = view.findViewById(R.id.nc_show_translation_clickable)
        val ncTeacherDetailTranslateContainer: LinearLayout = view.findViewById(R.id.nc_teacher_introduction_translate_container)
        val ncTeacherArrowView: ImageView = view.findViewById(R.id.nc_teacher_arrow_view)
        val ncTeacherNationalityView: ImageView = view.findViewById(R.id.nc_teacher_nationality_view)
        val ncTeacherNationalityCountry: TextView = view.findViewById(R.id.nc_teacher_current_address_country)
        val ncTeacherSuddenLessonCoinReq: TextView = view.findViewById(R.id.nc_teacher_sudden_coins_required)
        val ncTeacherBookedLessonCoinReq: TextView = view.findViewById(R.id.nc_teacher_booked_coins_required)
        val ncTeacherFeaturesContainer: LinearLayout = view.findViewById(R.id.nc_teacher_features_container)
        val ncTeacherHobbiesContainer: LinearLayout = view.findViewById(R.id.nc_teacher_hobbies_container)

        ncTeacherIntroduction.text = teacherDetail.message
        ncTeacherIntroductionTranslate.text = teacherDetail.messageTranslation

        ncTeacherClickableShowTranslation.setOnClickListener {
            val arrowDown = ContextCompat.getDrawable(requireContext(), R.drawable.arrow_down)
            val arrowUp = ContextCompat.getDrawable(requireContext(), R.drawable.arrow_up)

            if(ncTeacherDetailTranslateContainer.visibility == View.GONE) {
                ncTeacherDetailTranslateContainer.visibility = View.VISIBLE
                ncTeacherArrowView.setImageDrawable(arrowUp)
            } else {
                ncTeacherDetailTranslateContainer.visibility = View.GONE
                ncTeacherArrowView.setImageDrawable(arrowDown)
            }
        }

        Glide.with(requireContext())
            .load(teacherDetail.residenceImage)
            .into(ncTeacherNationalityView)

        ncTeacherNationalityCountry.text = teacherDetail.residenceName

        if(teacherDetail.coinLessonNow.toInt() == 0 || teacherDetail.coinLessonNow.toString() == "") {
            ncTeacherSuddenLessonCoinReq.text = "No coins required"
        } else {
            ncTeacherSuddenLessonCoinReq.text = "${teacherDetail.coinLessonNow} coins"
        }

        if(teacherDetail.coinLesson.toInt() == 0 || teacherDetail.coinLesson.toString() == "") {
            ncTeacherBookedLessonCoinReq.text = "No coins required"
        } else {
            ncTeacherBookedLessonCoinReq.text = "${teacherDetail.coinLesson} coins"
        }

        if(teacherDetail.features.length() > 0 && teacherDetail.features != null) {
            for(i in 0 until teacherDetail.features.length()) {
                val textView = TextView(requireContext())
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.nc_white)

                textView.text = teacherDetail.features.getString(i)
                textView.setPadding(16, 6, 8, 16)
                textView.setTextColor(whiteColor)
                textView.setBackgroundResource(R.drawable.teacher_hightlights_container)

                ncTeacherFeaturesContainer.addView(textView)
            }
        }

        if(teacherDetail.hobbies.length() > 0 && teacherDetail.hobbies != null) {
            for(i in 0 until teacherDetail.hobbies.length()) {
                val textView = TextView(requireContext())
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.nc_white)

                textView.text = teacherDetail.hobbies.getString(i)
                textView.setPadding(16, 6, 8, 16)
                textView.setTextColor(whiteColor)
                textView.setBackgroundResource(R.drawable.teacher_hightlights_container)

                ncTeacherHobbiesContainer.addView(textView)
            }
        }
    }
}
