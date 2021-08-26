package com.example.userregistration.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.userregistration.R
import com.example.userregistration.databinding.ActivityLoginBinding
import com.example.userregistration.models.LoginRequest
import com.example.userregistration.viewmodel.LoginViewModel

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences:SharedPreferences
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.btnLogin.setOnClickListener {

            var password = binding.etpassword.text.toString()
            var error = true
            if (password.isEmpty()) {
                error = true
                binding.etpassword.setError("Password is required")
            }

            var email = binding.etusername.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etusername.setError("Email is required")
            }
            var loginRequest = LoginRequest(
                email = email, password = password
            )
            loginViewModel.login(loginRequest)
        }
            loginViewModel.loginLiveData.observe(this, { logResponse ->
                Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_LONG).show()
                var editor=sharedPreferences.edit()
                editor.putString("ACCESS_TOKEN",logResponse.access_token).apply()
                editor.putString("STUDENT_ID",logResponse.student_id).apply()
                var intent = Intent(baseContext, CoursesActivity::class.java)
                startActivity(intent)
            })
            loginViewModel.loginFailedLiveData.observe(this, { error ->
                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

            })
        }
}

