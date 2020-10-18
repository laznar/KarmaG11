package com.uninorte.a_202030_firebaseapplication.ui.content

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.utils.karmaNode
import com.uninorte.a_202030_firebaseapplication.utils.usernameNode
import com.uninorte.a_202030_firebaseapplication.utils.usersNode
import com.uninorte.a_202030_firebaseapplication.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_favor.*
import kotlinx.android.synthetic.main.fragment_profile.*


class FavorFragment : Fragment(R.layout.fragment_favor) {

    private lateinit var auth: FirebaseAuth
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private var puntos : Int = 0

    init {
        auth = Firebase.auth

    }

    var currentUser = auth.currentUser





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentUser = auth.currentUser
        val karmaResponse = MutableLiveData<String>()

        profileViewModel.getProfileData()


        profileViewModel.getKarmaData().observe(viewLifecycleOwner, Observer { karma ->
            Log.d("MyOut","FavorFragment karma is "+karma)
            karmapoints.text = karma
        })

        /*fun getKarmaData() {
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
                    karmaResponse.value = karma
                    puntosTextView.text = karma
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }

        getKarmaData()*/


        /*val radioButtonID: Int = radiogroupButton.checkedRadioButtonId
        val radioButton: View = radiogroupButton.findViewById(radioButtonID)
        val idx: Int = radiogroupButton.indexOfChild(radioButton)
        val r  = radiogroupButton.getChildAt(idx) as RadioGroup
        val selectedtext: String = r.toString()
        Log.i(TAG, "onViewCreated: "+selectedtext)*/
        
        /*radiogroupButton.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->})
            if(i == R.id.fotocopiasButton){
            hacerVisible(codigoTextNumber)
            hacerInvisible(objetoPlainText)
            hacerInvisible(cantidadTextNumber)
            hacerInvisible(editTextTextMultiLine)
        }
            else if(i == R.id.comprarkm5Button){
            hacerInvisible(codigoTextNumber)
            hacerVisible(objetoPlainText)
            hacerVisible(cantidadTextNumber)
            hacerInvisible(editTextTextMultiLine)
        }
            else if(i== R.id.domicilioButton){
            hacerInvisible(codigoTextNumber)
            hacerInvisible(objetoPlainText)
            hacerInvisible(cantidadTextNumber)
            hacerVisible(editTextTextMultiLine)
        }
            })*/

        homeButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_favorFragment_to_profileFragment)
        }

        requestFavorButton.setOnClickListener {
            profileViewModel.getProfileData()
            puntos = Integer.parseInt(karmapoints.text.toString())
            if(puntos>=2){
                Toast.makeText(this.requireContext(), "Su favor ha sido iniciado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.requireContext(), "Debe de tener 2 o más de karma", Toast.LENGTH_SHORT).show()
            }
        }



    }
}