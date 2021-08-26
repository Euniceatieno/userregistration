package com.example.userregistration.repository

import com.example.userregistration.api.ApiClient
import com.example.userregistration.api.ApiInterface
import com.example.userregistration.models.Courses
import com.example.userregistration.models.EnrollResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun getCourses(accessToken: String): Response<List<Courses>> =
  withContext(Dispatchers.IO){
      return@withContext apiInterface.getCourses(accessToken)
  }
    suspend fun enrolStudent(accessToken: String):
            Response<EnrollResponse> = withContext(Dispatchers.IO){
        var response=apiInterface.enrollStudent(accessToken)
        return@withContext response

    }
}

