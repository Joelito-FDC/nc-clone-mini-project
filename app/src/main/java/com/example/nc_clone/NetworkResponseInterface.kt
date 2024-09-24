package com.example.nc_clone

import org.json.JSONObject

interface NetworkResponseInterface {
    fun success(responseOne: MutableList<Teacher>?, responseTwo: TeacherDetail?, responseThree: NCPlusContent?)
    fun error(error: String)
}