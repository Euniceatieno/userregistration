package com.example.userregistration.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.userregistration.R
import com.example.userregistration.models.Courses

class CoursesListAdapter(var courseList: List<Courses>):RecyclerView.Adapter<CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.course_list_items,parent,false)
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
      var currentCourses=courseList[position]
        holder.course_id.text=currentCourses.course_id
        holder.course_name.text=currentCourses.course_name
        holder.course_code.text=currentCourses.course_code
        holder.description.text=currentCourses.description
        holder.instructor.text=currentCourses.instructor
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

}

class CoursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val course_id=itemView.findViewById<TextView>(R.id.tvCourseId)
    val course_name=itemView.findViewById<TextView>(R.id.tvCourseName)
    val course_code=itemView.findViewById<TextView>(R.id.tvCourseCode)
    val description=itemView.findViewById<TextView>(R.id.tvDescription)
    var instructor=itemView.findViewById<TextView>(R.id.tvInstructor)
}