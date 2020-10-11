package com.uninorte.a_202030_firebaseapplication.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*



@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_auth) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyOut","LoginFragment onViewCreated")
        firebaseAuthViewModel.userCreated().observe(viewLifecycleOwner, Observer { status ->
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

        firebaseAuthViewModel.logged().observe(viewLifecycleOwner, Observer { uid ->
            Log.d("MyOut","LoginFragment logged with "+uid)
            if (uid != ""){
                Toast.makeText(
                    this.requireContext(), "Logged Ok."+uid,
                    Toast.LENGTH_SHORT
                ).show()
                view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
            } else {
                Toast.makeText(
                    this.requireContext(), "Logged failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })



        signInButton.setOnClickListener {
            Log.d("MyOut","Sign In")
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            firebaseAuthViewModel.signIn(email,password)
        }

        signupPassButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


    }
}