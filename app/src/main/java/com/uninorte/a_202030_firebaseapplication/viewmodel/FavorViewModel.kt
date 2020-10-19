package com.uninorte.a_202030_firebaseapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_firebaseapplication.repository.FavorRepository


class FavorViewModel : ViewModel() {
    private val repository = FavorRepository()

    fun createFavor(type:String,details:String){
        repository.createFavor(type,details)
    }


}