package com.example.userregistration

import com.example.userregistration.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("students/register")
    suspend fun registerStudent(@Body registrationRequest:RegistrationRequest): Response<RegistrationResponse>

    @POST("students/login")
    suspend fun loginStudent(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/courses")
    suspend fun studentCourses(@Header("Authorization") token: String): Response<List<CourseResponse>>

}