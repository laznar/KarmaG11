package com.uninorte.a_202030_firebaseapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Karma
import com.uninorte.a_202030_firebaseapplication.model.User
import com.uninorte.a_202030_firebaseapplication.utils.PreferenceProvider
import com.uninorte.a_202030_firebaseapplication.utils.karmaNode
import com.uninorte.a_202030_firebaseapplication.utils.usernameNode
import com.uninorte.a_202030_firebaseapplication.utils.usersNode

class ProfileRepository {


    val karmaList = mutableListOf<Karma>()
    val profileResponse = MutableLiveData<String>()
    val karmaResponse = MutableLiveData<String>()
    val profileList = mutableListOf<User>()
    private val database = Firebase.database.reference

    init {
        profileResponse.value= ""
        karmaResponse.value=""
    }


    fun getProfileData() {
        // [favor.user] tells me who created the current favor, get the username of that user and display it
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser!!.uid
        val databaseReference =
            FirebaseDatabase.getInstance().getReference(usersNode).child(user)
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val username = snapshot.child(usernameNode).value.toString()
                val karma = snapshot.child(karmaNode).value.toString()
                profileResponse.value = username
                karmaResponse.value = karma
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }





    /*fun observeProfileData() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                profileList.clear()
                for (childDataSnapshot in dataSnapshot.children) {
                    val user: User = childDataSnapshot.getValue(User::class.java)!!
                    profileList.add(user)
                }
                profileResponse.value = profileList

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MyOut", "loadPost:onCancelled", databaseError.toException())
            }
        }
    }*/

    fun logOut(){
        PreferenceProvider.setLogged("")
        Firebase.auth.signOut()
    }




}