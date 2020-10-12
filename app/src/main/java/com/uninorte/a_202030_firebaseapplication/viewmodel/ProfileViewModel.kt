package com.uninorte.a_202030_firebaseapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_firebaseapplication.repository.ProfileRepository

class ProfileViewModel: ViewModel() {
    private val repository = ProfileRepository()
    fun getKarma()=repository.getKarma()
    fun getUsername()=repository.getUsername()



}