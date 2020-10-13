package com.uninorte.a_202030_firebaseapplication.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {


    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyOut","LoginFragment onViewCreated")

        firebaseAuthViewModel.userCreated().observe(getViewLifecycleOwner(), Observer { status ->
            if (status == true){
                Toast.makeText(
                    this.requireContext(), "Authentication Ok.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this.requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        firebaseAuthViewModel.logged().observe(getViewLifecycleOwner(), Observer { uid ->
            Log.d("MyOut","LoginFragment logged with "+uid)
            if (uid != ""){
                Toast.makeText(
                    this.requireContext(), "Logged Ok."+uid,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this.requireContext(), "Logged failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })


        registerButton.setOnClickListener {
            Log.d("MyOut","Sign Up")
            val email = emailPlainText.text.toString()
            val username = usernamePlainText.text.toString()
            val key = passwordEditText.text.toString()
            firebaseAuthViewModel.signUp(email,key,username)
        }

        backtoLoginButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }
}