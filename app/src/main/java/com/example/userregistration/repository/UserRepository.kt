package com.example.userregistration.repository

import com.example.userregistration.ApiInterface
import com.example.userregistration.api.ApiClient
import com.example.userregistration.models.LoginRequest
import com.example.userregistration.models.LoginResponse
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerStudent(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
    withContext(Dispatchers.IO){
        var response = apiInterface.registerStudent(registrationRequest)
        return@withContext response
    }
    suspend fun loginStudent(loginRequest: LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO){
            var response = apiInterface.loginStudent(loginRequest)
            return@withContext response
}
}