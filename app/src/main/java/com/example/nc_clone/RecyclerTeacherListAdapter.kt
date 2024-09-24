package com.example.nc_clone

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson


class RecyclerTeacherListAdapter(private val teacherList: MutableList<Teacher>, private val context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerTeacherListAdapter.TeacherInfoView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherInfoView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.teacher_list_view_container, parent, false)

        return TeacherInfoView(view)
    }

    override fun onBindViewHolder(
        holder: TeacherInfoView,
        position: Int
    ) {
        val teacher = teacherList[position]

        holder.teacherNameTextView.text = teacher.name

        // Teacher profile
        Glide.with(context)
            .load(teacher.imageProfile)
            .placeholder(R.drawable.user)
            .error(R.drawable.user)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
            .into(holder.teacherImageView)

        // Teacher nationality flag
        Glide.with(context)
            .load(teacher.nationalityFlag)
            .into(holder.teacherNationalityFlagView)

        holder.teacherNationalityNameView.text = teacher.nationality
        holder.teacherFavoriteCountView.text = teacher.favorite_count.toString()
        holder.teacherStarRatingView.text = teacher.rating.toString()
        holder.teacherMonitorRatingView.text = teacher.lessons.toString()
        holder.teacherCostView.text = teacher.teacherReserveCoin.toString()
        holder.teacherAgeView.text = "(Age: ${teacher.age.toString()})"

        holder.teacherMainContainerView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = teacherList.size

    class TeacherInfoView(item: View) : RecyclerView.ViewHolder(item) {
        val teacherNameTextView: TextView = item.findViewById(R.id.nc_teacher_name)
        val teacherImageView: ImageView = item.findViewById(R.id.nc_teacher_image)
        val teacherNationalityFlagView: ImageView = item.findViewById(R.id.nc_teacher_nationality)
        val teacherNationalityNameView: TextView = item.findViewById(R.id.nc_teacher_nationality_name)
        val teacherFavoriteCountView: TextView = item.findViewById(R.id.nc_teacher_favorite_count)
        val teacherStarRatingView: TextView = item.findViewById(R.id.nc_teacher_star_rating)
        val teacherMonitorRatingView: TextView = item.findViewById(R.id.nc_teacher_monitor_rating)
        val teacherCostView: TextView = item.findViewById(R.id.nc_teacher_cost)
        val teacherAgeView: TextView = item.findViewById(R.id.nc_teacher_age)
        val teacherMainContainerView: ConstraintLayout = item.findViewById(R.id.nc_teacher_info_main_container)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}