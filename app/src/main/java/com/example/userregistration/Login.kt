package com.example.userregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.userregistration.api.ApiClient
import com.example.userregistration.models.LoginRequest
import com.example.userregistration.models.LoginResponse
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var etusername:TextInputEditText
    lateinit var etpassword:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        castViews()
    }
    fun castViews(){
        var error=false
        etusername=findViewById(R.id.etusername)
        etpassword=findViewById(R.id.etpassword)
        btnLogin=findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var password = etpassword.text.toString()
            if(password.isEmpty()){
                error = true
                etpassword.setError("Password is required")
            }

            var email = etusername.text.toString()
            if(email.isEmpty()){
                error=true
                etusername.setError("Email is required")
            }
            var loginRequest = LoginRequest(
               email=email,  password=password
            )
            var client = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = client.loginStudent(loginRequest )

            request.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        var intent = Intent(baseContext, Courses::class.java)
                        startActivity(intent)
                    } else {
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG)
                                .show()
                        } catch (e: Exception) {
                            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

}



