package com.uninorte.a_202030_firebaseapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Karma

class ProfileRepository {

    val karmaResponse = MutableLiveData<List<Karma>>()
    val karmaList = mutableListOf<Karma>()
    private val database = Firebase.database.reference
    fun getKarma() = karmaResponse

    fun showKarma() {
        val valueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                karmaList.clear()
                for (childDataSnapshot in dataSnapshot.children) {
                    val karma: Karma = childDataSnapshot.getValue(Karma::class.java)!!
                    karmaList.add(karma)
                }
                karmaResponse.value = karmaList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MyOut", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.child("karma").addValueEventListener(valueListener)
    }

}