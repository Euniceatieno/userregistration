package com.example.userregistration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.userregistration.api.ApiClient
import com.example.userregistration.databinding.ActivityMainBinding
import com.example.userregistration.models.RegistrationRequest
import com.example.userregistration.models.RegistrationResponse
import com.example.userregistration.ui.CoursesListAdapter
import com.example.userregistration.ui.Login
import com.example.userregistration.viewmodel.UserViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var sharedPreferences:SharedPreferences
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)

    }
    fun redirect(){
        var accessToken=sharedPreferences.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        if (accessToken!!.isNotEmpty()){
            startActivity(Intent(baseContext,CoursesListAdapter::class.java))
        }
        else{
            startActivity(Intent(baseContext, Login::class.java))
        }
    }
    override fun onResume(){
        super.onResume()

        var nationalities= arrayOf("KENYAN","UGANDAN","RWANDAN","SOUTH SUDAN","TANZANIAN")
        var nationalityAdapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nationalities)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnationality.adapter=nationalityAdapter

        binding.btnRegister.setOnClickListener{
            var error = false
            var name = binding.etname.text.toString()
            if(name.isEmpty()){
                error = true
                binding.etname.setError("Name is required")
            }
            var dob = binding.etdob.text.toString()
            if(dob.isEmpty()){
                error = true
                binding.etdob.setError("Field must be filled")
            }
            var nationality = binding.spnationality.selectedItem.toString()


            var password = binding.etPassword.text.toString()
            if(password.isEmpty()){
                error = true
                binding.etPassword.setError("Password is required")
            }
            var phone = binding.etphone.text.toString()
            if(phone.isEmpty()){
                error = true
                binding.etphone.setError("Phone Number is required")
            }
            var email = binding.etemail.text.toString()
            if(email.isEmpty()){
                error=true
                binding.etemail.setError("Email is required")
            }

            var regRequest=RegistrationRequest(name = binding.etname.text.toString(),
                phoneNumber = binding.etphone.text.toString(),
                email = binding.etemail.text.toString(),
                dateOfBirth = binding.etdob.text.toString(),
                nationality = binding.spnationality.selectedItem.toString(),
                password = binding.etPassword.text.toString())
            userViewModel.registerUser(regRequest)

            userViewModel.registrationLiveData.observe(this,{ regResponse->
                Toast.makeText(baseContext,"Registration Successful",Toast.LENGTH_LONG).show()
                var intent=Intent(baseContext,Login::class.java)
                startActivity(intent)
            })
            userViewModel.regFailedLiveData.observe(this,{ error->
                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()

            })
        }

        binding.btnLog.setOnClickListener {
            startActivity(Intent(baseContext,Login::class.java))
        }
    }
}





