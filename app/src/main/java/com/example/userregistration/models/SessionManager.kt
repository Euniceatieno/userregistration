package com.example.userregistration.models

import android.content.Context
import android.content.SharedPreferences


class SessionManager(context: Context) {
    var sharedPreferences: SharedPreferences = context.getSharedPreferences("HELLO_WORLD_2",Context.MODE_PRIVATE)
    fun getToken(token:String){
        sharedPreferences.edit().putString("ACCESS_TOKEN",token).apply()
    }
    fun getToken():String?{
        return sharedPreferences.getString("ACCESS_TOKEN","")
    }
}