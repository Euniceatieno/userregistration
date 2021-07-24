package com.example.userregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Courses : AppCompatActivity() {
    lateinit var rvCourses:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()
    }
    fun displayCourses(){
        rvCourses=findViewById(R.id.rvCourses)
    var courseList= listOf(
        Course("123kotlin","Mobile Development",
            "Introduction to Android Studio",
            "John Owuor"),
        Course("123Python","Python",
            "Introduction to Django",
            "James Mwai"),
        Course("123js","FrontEnd Development",
            "Introduction to Aurellia",
            "Purity Maina"),
        Course("123Design","UI/UX Design",
            "Introduction to Adobe Design",
            "Erick Asiage")
    )
    var coursesAdapter=CoursesAdapter(courseList)
    rvCourses.layoutManager= LinearLayoutManager(baseContext)
    rvCourses.adapter=coursesAdapter

}}