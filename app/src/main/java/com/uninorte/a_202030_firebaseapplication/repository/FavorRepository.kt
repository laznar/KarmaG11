package com.uninorte.a_202030_firebaseapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Favor
import com.uninorte.a_202030_firebaseapplication.utils.favorsNode
import com.uninorte.a_202030_firebaseapplication.utils.init

class FavorRepository {

    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database.reference
    private val favorList =  MutableLiveData<ArrayList<Favor>>()

    init {
        auth = Firebase.auth
    }

    fun writeFavor(favor: Favor){
        database.child(favorsNode).setValue(favor)
    }

    fun createFavor(type:String,details:String){
        val user = auth.currentUser
        val uid = user!!.uid
        val id = (0..1000).random()
        val dbRef = FirebaseDatabase.getInstance().getReference(favorsNode)
        val key = dbRef.push().key.toString()
        dbRef.child(key).setValue(Favor(user.uid,key,id,type,init,details))
    }

    /*fun createFavor(key: String, id: String){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val favors = ArrayList<Favor>()
                    for (dataSnapshot: DataSnapshot in snapshot.children) {
                        val favor = dataSnapshot.getValue(Favor::class.java)
                            favor.let {
                                if (it != null) {
                                    favors.add(it)
                                }
                            }
                    }
                    // Now the 'local' favors into the LiveData (I'll be observing it)
                    favors.reverse()    // To show first, the most recent one (not the best solution, I know)
                    favorList.value = favors
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }*/

}