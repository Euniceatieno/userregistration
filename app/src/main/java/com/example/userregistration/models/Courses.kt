package com.example.userregistration.models

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName("course_name")var course_name:String,
    @SerializedName("course_id")var course_id:String,
    @SerializedName("course_code") var course_code:String,
    var description:String,
    var instructor:String
)
