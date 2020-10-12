package com.uninorte.a_202030_firebaseapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Karma
import com.uninorte.a_202030_firebaseapplication.model.User

class ProfileRepository {

    val karmaResponse = MutableLiveData<List<Karma>>()
    val karmaList = mutableListOf<Karma>()
    val userResponse = MutableLiveData<List<User>>()
    val userList = mutableListOf<User>()
    private val database = Firebase.database.reference
    fun getKarma() = karmaResponse
    fun getUser() = userResponse

    init{
        viewUser()
    }


    fun viewUser(){
        val valueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                userList.clear()
                for (childDataSnapshot in dataSnapshot.children) {
                    val usuarios: User = childDataSnapshot.getValue(User::class.java)!!
                    userList.add(usuarios)
                }
                userResponse.value = userList

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MyOut", "loadPost:onCancelled", databaseError.toException())
            }

        }
        database.child("users").addValueEventListener(valueListener)
        showKarma()
    }

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