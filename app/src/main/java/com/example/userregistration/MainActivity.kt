package com.example.userregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.userregistration.api.ApiClient
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etname:EditText
    lateinit var etdob:EditText
    lateinit var etphone:EditText
    lateinit var spnationality:Spinner
    lateinit var etemail:EditText
    lateinit var etPassword:EditText
    lateinit var btnRegister:Button
    lateinit var btnlog:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViews()
        clickRegister()
        loginClick()
    }
    fun castViews(){
        etname=findViewById(R.id.etname)
        etdob=findViewById(R.id.etdob)
        etPassword=findViewById(R.id.etPassword)
        etphone=findViewById(R.id.etphone)
        etemail=findViewById(R.id.etemail)
        btnRegister=findViewById(R.id.btnRegister)
        btnlog=findViewById(R.id.btnLog)
        spnationality=findViewById(R.id.spnationality)




        var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
        var nationalitiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnationality.adapter = nationalitiesAdapter


    }
    fun loginClick(){
        btnlog.setOnClickListener {
            var intent =Intent(baseContext,Login::class.java)
            startActivity(intent)
        }
    }

    fun clickRegister(){
        var error = false
        btnRegister.setOnClickListener {
            var name = etname.text.toString()
            if(name.isEmpty()){
                error = true
                etname.setError("Name is required")
            }
            var dob = etdob.text.toString()
            if(dob.isEmpty()){
                error = true
                etdob.setError("Field must be filled")
            }
            var nationality = spnationality.selectedItem.toString()

            var password = etPassword.text.toString()
            if(password.isEmpty()){
                error = true
                etPassword.setError("Password is required")
            }
            var phone = etphone.text.toString()
            if(phone.isEmpty()){
                error = true
                etphone.setError("Phone Number is required")
            }
            var email = etemail.text.toString()
            if(email.isEmpty()){
                error=true
                etemail.setError("Email is required")
            }

            var registrationRequest = RegistrationRequest(
                name=name, phoneNumber=phone, email=email, nationality=nationality.toUpperCase(),
                dateOfBirth=dob, password=password
            )

            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = retrofit.registerStudent(registrationRequest)
            request.enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    if (response.isSuccessful){
                        Toast.makeText(baseContext, "Registration Successful", Toast.LENGTH_LONG).show()
                        var intent =Intent(baseContext,Login::class.java)
                        startActivity(intent)
                    }
                    else{
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG)
                                .show()
                        } catch (e: Exception) {
                            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

}

data class ApiError(var errors: List<String>)