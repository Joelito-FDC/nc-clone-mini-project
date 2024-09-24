package com.example.nc_clone

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class NetworkCalls(private val NCActivityContext: Context) {
    fun getTeacherList(responseHandler: NetworkResponseInterface) {
        val url = "https://english-staging.fdc-inc.com/api/teachers/search?src_view=home_teacher"
        val postData = JSONObject()
        val requestCondition = JSONObject()

        requestCondition.put("lesson_course", 0)
        requestCondition.put("connect_id", 0)
        requestCondition.put("hide_native_teacher", 0)
        requestCondition.put("show_callan_unli_teacher", 0)
        requestCondition.put("age", arrayOf(2, 3, 4, 5))

        postData.put("users_api_token", "b0923563872bf5214db3444b0517aada")
        postData.put("conditions", requestCondition)
        postData.put("pagination", 1)
        postData.put("order", 2)
        postData.put("avatar_flg", 0)
        postData.put("limit", 20)
        postData.put("account_status", 3)
        postData.put("device_type", 2)
        postData.put("app_version", "5.2.5")
        postData.put("api_version", 30)

        val requestDetails = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {
                val allTeachers = it.getJSONArray("teachers")
                val teachers = mutableListOf<Teacher>()

                for(i in 0 until allTeachers.length()) {
                    val t = allTeachers.getJSONObject(i)

                    teachers.add(Teacher(
                        id = t.getInt("id"),
                        name = t.getString("name_eng"),
                        age = t.getInt("age"),
                        nationality = t.getString("country_name"),
                        nationalityFlag = t.getString("country_image"),
                        favorite_count = t.getInt("favorite_count"),
                        lessons = t.getInt("lessons"),
                        imageProfile = t.getString("image_main"),
                        teacherReserveCoin = t.getInt("teacher_reserve_coin"),
                        rating = t.optDouble("rating", 0.00)
                    ))
                }

                responseHandler.success(teachers, null, null)
            },
            { responseHandler.error(it.message.toString()) }
        )

        val requestQueue = Volley.newRequestQueue(NCActivityContext)

        requestQueue.add(requestDetails)
    }

    fun getTeacherDetails(responseHandler: NetworkResponseInterface, teacherId: Int) {
        val url = "https://english-staging.fdc-inc.com/api/teachers/detail"
        val postData = JSONObject()

        postData.put("users_api_token", "b1c620715abe49b8ee506d9df480cd1b")
        postData.put("teachers_id", teacherId)
        postData.put("check_sapuri_user", false)
        postData.put("emergency_lesson", 0)
        postData.put("api_version", 30)
        postData.put("app_version", "5.2.5")
        postData.put("device_type", 2)

        val requestDetails = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {
                val teacher = it.getJSONObject("teacher")
                val profileImage = teacher.getJSONObject("images")
                val kidsRating = teacher.getJSONArray("kids_ratings")
                val rating = teacher.getJSONArray("ratings")
                var feature: JSONArray
                var hobbies: JSONArray

                try {
                    feature = teacher.getJSONArray("features")
                } catch(e: JSONException) {
                    feature = JSONArray()
                }

                try {
                    hobbies = teacher.getJSONArray("hobbies")
                } catch(e: JSONException) {
                    hobbies = JSONArray()
                }

                Log.d(feature.toString(), "test-here-again-testing")

                var kRating: Double = 0.0
                var rate: Double = 0.0

                for(i in 0 until kidsRating.length()) {
                    kRating += kidsRating.getInt(i)
                }

                for(i in 0 until rating.length()) {
                    rate += rating.getInt(i)
                }

                responseHandler.success(null, TeacherDetail(
                    id = teacher.getInt("id"),
                    name = teacher.getString("name_eng"),
                    age = teacher.getInt("age"),
                    imageProfile = profileImage.getString("main"),
                    kidsRating = kRating / kidsRating.length(),
                    country = teacher.getString("country_name_eng"),
                    countryImage = teacher.getString("country_image"),
                    rating = rate / rating.length(),
                    favoriteCount = teacher.getInt("favorite_count"),
                    lesson = teacher.getInt("lessons"),
                    message = teacher.getString("message"),
                    messageTranslation = teacher.getString("message_translation"),
                    residenceImage = teacher.getString("residence_image"),
                    residenceName = teacher.getString("residence_name"),
                    coinLessonNow = teacher.getInt("coin_lesson_now"),
                    coinLesson = teacher.getInt("coin_reservation"),
                    features = feature,
                    hobbies = hobbies
                ), null)
            },
            { responseHandler.error(it.message.toString()) }
        )

        val requestQueue = Volley.newRequestQueue(NCActivityContext)

        requestQueue.add(requestDetails)
    }

    fun getNCPlusContent(responseHandler: NetworkResponseInterface) {
        val url = "https://english-staging.fdc-inc.com/api/home-screen/nc-plus"
        val postData = JSONObject()
        val dataParam = JSONObject()

        postData.put("users_api_token", "b1c620715abe49b8ee506d9df480cd1b")
        dataParam.put("check_user_setting", 1)
        postData.put("data", dataParam)
        dataParam.put("api_version", 30)
        dataParam.put("app_version", "5.2.5")
        dataParam.put("device_type", 2)

        val requestDetails = JsonObjectRequest(
            Request.Method.POST,
            url,
            postData,
            {
                Log.d("function-here", "function-test-here")
                val ncPlusContent = it.getJSONObject("content_list")
                val dailyNewsLatest = ncPlusContent.getJSONObject("vocabulary_learning").getJSONObject("details").getJSONArray("categories")

                Log.d(dailyNewsLatest.toString(), "test-here-network-call")

                responseHandler.success(null, null, NCPlusContent(
                    dailyNewsLatest = dailyNewsLatest
                ))
            },
            { responseHandler.error(it.message.toString()) }
        )

        val requestQueue = Volley.newRequestQueue(NCActivityContext)

        requestQueue.add(requestDetails)
    }
}