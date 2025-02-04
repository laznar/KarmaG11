package com.uninorte.a_202030_firebaseapplication.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.User
import com.uninorte.a_202030_firebaseapplication.utils.usersNode

class FirebaseAuthRepository {

    private lateinit var auth: FirebaseAuth
    val database = Firebase.database.reference
    var logged = MutableLiveData<String>()
    var userCreated = MutableLiveData<Boolean>()

    init {
        auth = Firebase.auth
        logged.value = ""
    }

    fun writeNewUser(user: User){
        database.child(usersNode).push().setValue(user)
    }

    fun signUp(email: String, password : String, username: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MyOut", "createUserWithEmail:success")
                    val user = auth.currentUser
                    val uid = user!!.uid
                    val dbRef = database.child(usersNode).child(uid)
                    if (user != null) {
                        dbRef.setValue(User(user.email, user.uid,username,5))
                        //writeNewUser(User(user.email, user.uid,username,5))
                    }
                    userCreated.value = true;
                } else {
                    Log.d("MyOut", "createUserWithEmail:failure", task.exception)
                    userCreated.value = false;
                }
            }
    }

    fun signIn(email: String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        Log.d("MyOut", "signInWithEmailAndPassword:success " + user.email)
                        Log.d("MyOut", "signInWithEmailAndPassword:success " + user.uid)
                        logged.value = user.uid
                    }

                } else {
                    logged.value = ""
                    Log.d("MyOut", "signInWithEmailAndPassword:failure", task.exception)
                }
            }
    }

    fun logOut(){
        logged.value = ""
        Firebase.auth.signOut()
    }


}