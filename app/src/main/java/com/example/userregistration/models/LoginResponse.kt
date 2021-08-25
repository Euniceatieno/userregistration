package com.example.userregistration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    var message:String,
    @SerializedName("access_token") var access_token:String,
    @SerializedName("student_id") var student_id:String
)
