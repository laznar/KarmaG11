package com.uninorte.a_202030_firebaseapplication.ui.content

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.utils.karmaNode
import com.uninorte.a_202030_firebaseapplication.utils.usernameNode
import com.uninorte.a_202030_firebaseapplication.utils.usersNode
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel: FirebaseRealTimeDBViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth

    init {
        auth = Firebase.auth

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentUser = auth.currentUser
        var username : String? = ""
        var emailUser : String? = currentUser?.email!!
        val profileResponse = MutableLiveData<String>()
        val karmaResponse = MutableLiveData<String>()

        profileViewModel.getProfileData()


        profileViewModel.getKarmaData().observe(viewLifecycleOwner, Observer { karma ->
            Log.d("MyOut","ProfileFragment karma is "+karma)
            puntosTextView.text = karma
        })

        profileViewModel.getUsernameData().observe(viewLifecycleOwner, Observer { username ->
            Log.d("MyOut","ProfileFragment username is "+username)
            usernameTextView.text = username
        })


        /*fun getProfileData() {
            // [favor.user] tells me who created the current favor, get the username of that user and display it
            val auth = FirebaseAuth.getInstance()
            val user = auth.currentUser!!.uid
            val databaseReference =
                FirebaseDatabase.getInstance().getReference(usersNode).child(user)
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val username = snapshot.child(usernameNode).value.toString()
                    val karma = snapshot.child(karmaNode).value.toString()
                    Log.i("TAG",username.toString())
                    Log.i("TAG",karma.toString())
                    profileResponse.value = username
                    karmaResponse.value = karma
                    puntosTextView.text = karma
                    usernameTextView.text = username
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }

        getProfileData()*/






        /*fun currentUserReference() = firebaseRealTimeDBViewModelViewModel.database.child("users").child(auth.currentUser!!.uid)
        currentUserReference().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    val karma = snapshot.child("karma").getValue(User::class.java)
                    puntosTextView.text = karma.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("onCancelled: ", TAG)
            }
        })*/






        /*profileViewModel.getUsername().observe(viewLifecycleOwner, Observer {
            for(usuarios in it){
                if(usuarios.email == emailUser){
                    username = usuarios.username
                    usernameTextView.text = username
                }
            }
        })


        profileViewModel.getKarma().observe(viewLifecycleOwner, Observer {
            for (karma in it){
                if(karma.username == username){
                    puntosTextView.text = karma.points.toString()
                }
            }
        })*/


        requestButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_profileFragment_to_favorFragment)
        }

        logoutButton.setOnClickListener {
            firebaseAuthViewModel.logOut()
            view.findNavController().navigate(R.id.action_profileFragment_to_authActivity)
        }

        favorboardButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_profileFragment_to_messagesFragment)
        }
    }
}


