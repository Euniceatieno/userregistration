package com.example.userregistration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistration.Course
import com.example.userregistration.CoursesAdapter
import com.example.userregistration.R
import com.example.userregistration.databinding.ActivityCoursesBinding
import com.example.userregistration.models.CourseResponse
import com.example.userregistration.viewmodel.CoursesViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    val courseViewModel: CoursesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onResume() {
        super.onResume()
        courseViewModel.courses()
        var rvCourses = binding.rvCourses
        courseViewModel.courseLiveData.observe(this,{CourseResponse ->

            var coursesAdapter = CoursesAdapter(CourseResponse)
            rvCourses.layoutManager = LinearLayoutManager(baseContext)
            rvCourses.adapter = coursesAdapter


        })
    }

}