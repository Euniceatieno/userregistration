package com.example.userregistration.repository

import com.example.userregistration.ApiInterface
import com.example.userregistration.api.ApiClient
import com.example.userregistration.models.CourseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Response


class CoursesRepository {
    var apiInterface= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun courseRes(courseResponse: CourseResponse):
            Response<CourseResponse> =
        withContext(Dispatchers.IO) {
            var response = apiInterface.courseRes(courseResponse)
            return@withContext response
        }
}