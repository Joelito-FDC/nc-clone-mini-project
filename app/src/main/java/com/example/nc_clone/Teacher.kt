package com.example.nc_clone

data class Teacher(
    val id: Int,
    val name: String,
    val age: Int,
    val nationality: String,
    val nationalityFlag: String,
    val favorite_count: Int,
    val lessons: Int,
    val imageProfile: String,
    val teacherReserveCoin: Int,
    val rating: Double
)
