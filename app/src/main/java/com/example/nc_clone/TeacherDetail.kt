package com.example.nc_clone

import org.json.JSONArray

data class TeacherDetail(
    val id: Int,
    val name: String,
    val age: Int,
    val imageProfile: String,
    val kidsRating: Double,
    val country: String,
    val countryImage: String,
    val rating: Double,
    val favoriteCount: Int,
    val lesson: Int,
    val message: String,
    val messageTranslation: String,
    val residenceImage: String,
    val residenceName: String,
    val coinLessonNow: Int,
    val coinLesson: Int,
    val features: JSONArray,
    val hobbies: JSONArray
)
