package com.example.userregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userregistration.models.CourseResponse
import com.example.userregistration.repository.CoursesRepository
import kotlinx.coroutines.launch


class CoursesViewModel: ViewModel(){
    var courseLiveData = MutableLiveData<List<CourseResponse>>()
    var courseFailedLiveData = MutableLiveData<String>()
    var courseRepository = CoursesRepository()

    fun courses(){
        viewModelScope.launch {
            var response = courseRepository.courses()
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                courseFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}