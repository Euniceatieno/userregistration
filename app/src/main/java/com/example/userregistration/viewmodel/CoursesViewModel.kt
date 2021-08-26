package com.example.userregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userregistration.models.Courses
import com.example.userregistration.models.EnrollResponse
import com.example.userregistration.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel:ViewModel() {
    var coursesLiveData= MutableLiveData<List<Courses>>()
    var courseFailedLiveData= MutableLiveData<String>()

    var enrollLiveData=MutableLiveData<EnrollResponse>()
    var enrollFailedData=MutableLiveData<String>()

    var courseRepository= CoursesRepository()

    fun courses(accessToken:String){
        viewModelScope.launch {
            var response=courseRepository.getCourses(accessToken)
            if (response.isSuccessful){
                coursesLiveData.postValue(response.body())
            }
            else{
                courseFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun enrollStudent(accessToken: String){
        viewModelScope.launch {
            var response= courseRepository.enrolStudent(accessToken)
            if(response.isSuccessful){
                enrollLiveData.postValue(response.body())
            }
            else{
                enrollFailedData.postValue(response.errorBody()?.string())
            }
        }
    }
}