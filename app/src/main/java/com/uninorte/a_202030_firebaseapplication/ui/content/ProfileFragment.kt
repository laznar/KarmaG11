package com.uninorte.a_202030_firebaseapplication.ui.content

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.auth.User
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel : FirebaseRealTimeDBViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth





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
        /*fun currentUserReference() = firebaseRealTimeDBViewModelViewModel.database.child("users").child(auth.currentUser!!.uid)

        currentUserReference().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    val userd = snapshot.child("karma").getValue(User::class.java)
                    puntosTextView.text = userd.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("onCancelled: ", TAG)
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


