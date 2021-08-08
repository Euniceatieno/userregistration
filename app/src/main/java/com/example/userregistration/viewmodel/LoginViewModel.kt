package com.example.userregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userregistration.models.LoginRequest
import com.example.userregistration.models.LoginResponse
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import com.example.userregistration.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){

        var loginLiveData = MutableLiveData<LoginResponse>()
        var loginFailedLiveData = MutableLiveData<String>()
        var userRepository = UserRepository()
        fun login(LoginRequest: LoginRequest){
            viewModelScope.launch {
                val response =userRepository.loginStudent(LoginRequest)
                if (response.isSuccessful){
                    loginLiveData.postValue(response.body())
                }
                else{
                    loginFailedLiveData.postValue(response.errorBody()?.string())
                }

            }
        }
    }
