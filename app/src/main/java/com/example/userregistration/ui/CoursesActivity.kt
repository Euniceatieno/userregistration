package com.example.userregistration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userregistration.Constants
import com.example.userregistration.databinding.ActivityDisplayCoursesBinding
import com.example.userregistration.viewmodel.CoursesViewModel

class CoursesActivity : AppCompatActivity() {
  lateinit var binding:ActivityDisplayCoursesBinding
  lateinit var sharedPreferences:SharedPreferences
  val courseViewModel:CoursesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding= ActivityDisplayCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)

    }

    override fun onResume() {
        super.onResume()
        var accessToken=sharedPreferences.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        var bearer="Bearer $accessToken"
         if (accessToken!!.isNotEmpty()){
             courseViewModel.courses(bearer)
         }
        else{
            startActivity(Intent(baseContext, Login::class.java))
         }
        binding.rvCourses.layoutManager=LinearLayoutManager(baseContext)
        courseViewModel.coursesLiveData.observe(this, {course->
            if(!accessToken.isNullOrEmpty()){
                var coursesAdapter=CoursesListAdapter(course)
                binding.rvCourses.adapter=coursesAdapter
            }

        })
        courseViewModel.courseFailedLiveData.observe(this, {error->
            Toast.makeText(baseContext, error,Toast.LENGTH_LONG).show()

        })

    }
}