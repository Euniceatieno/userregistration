package com.example.userregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userregistration.models.Courses
import com.example.userregistration.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel:ViewModel() {
    var coursesLiveData = MutableLiveData<List<Courses>>()
    var courseFailedLiveData = MutableLiveData<String>()
    var courseRepository = CoursesRepository()
    fun courses(accessToken:String){
        viewModelScope.launch {
            val response =courseRepository.getCourses(accessToken)
            if (response.isSuccessful){
                coursesLiveData.postValue(response.body())
            }
            else{
                courseFailedLiveData.postValue(response.errorBody()?.string())
            }

        }
    }
}