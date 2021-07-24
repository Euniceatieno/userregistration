package com.example.userregistration

import com.example.userregistration.models.LoginRequest
import com.example.userregistration.models.LoginResponse
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("students/register")
    fun registerStudent(@Body registrationRequest:RegistrationRequest): Call<RegistrationResponse>

    @POST("students/login")
    fun loginStudent(@Body loginRequest: LoginRequest): Call<LoginResponse>

}