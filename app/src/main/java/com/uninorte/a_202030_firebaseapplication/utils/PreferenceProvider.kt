package com.uninorte.a_202030_firebaseapplication.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider {
    companion object{
        lateinit var preference : SharedPreferences
        fun initialize(context: Context) {
            preference = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
        }
        fun setLogged(uid : String){
            preference.edit().putString("uid",uid).apply()
        }
        fun getLogged(): String?{
            return preference.getString("uid","")
        }
    }
}