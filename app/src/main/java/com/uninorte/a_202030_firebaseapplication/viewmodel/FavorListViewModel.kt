package com.uninorte.a_202030_firebaseapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Favor
import com.uninorte.a_202030_firebaseapplication.utils.favorsNode



class FavorListViewModel : ViewModel() {
    val database = Firebase.database.reference

    var ldFavorList = MutableLiveData<List<Favor>>()
    val favorList = mutableListOf<Favor>()

    init{
        getValues()
    }


    fun getValues(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                favorList.clear()
                for (childDataSnapshot in dataSnapshot.children) {
                    val favor: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    favorList.add(favor)
                }
                ldFavorList.value = favorList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MyOut", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.child(favorsNode).addValueEventListener(postListener)
    }
}