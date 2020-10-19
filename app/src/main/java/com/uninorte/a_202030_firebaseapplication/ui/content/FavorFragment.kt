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
import com.uninorte.a_202030_firebaseapplication.utils.*
import com.uninorte.a_202030_firebaseapplication.viewmodel.FavorViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_favor.*
import kotlinx.android.synthetic.main.fragment_profile.*


class FavorFragment : Fragment(R.layout.fragment_favor) {

    private lateinit var auth: FirebaseAuth
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val favorViewModel: FavorViewModel by activityViewModels()
    private var puntos: Int = 0
    private var currentKarma : Int = 0
    private var type: String = ""
    private var fotsel : Boolean = false
    private var compsel : Boolean = false
    private var domsel : Boolean = false


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
            Log.d("MyOut", "FavorFragment karma is " + karma)
            karmapoints.text = karma
        })

        radiogroupButton.setOnCheckedChangeListener { group, checkedID ->
            if(checkedID == R.id.fotocopiasButton){
                fotsel = true
                hacerVisible(codigoTextNumber)
                hacerInvisible(objetoPlainText)
                hacerInvisible(cantidadTextNumber)
                hacerInvisible(detallesMultiLine)
            }
            if(checkedID == R.id.comprarkm5Button){
                compsel = true
                hacerInvisible(codigoTextNumber)
                hacerVisible(objetoPlainText)
                hacerVisible(cantidadTextNumber)
                hacerInvisible(detallesMultiLine)
            }
            if(checkedID == R.id.domicilioButton){
                domsel = true
                hacerInvisible(codigoTextNumber)
                hacerInvisible(objetoPlainText)
                hacerInvisible(cantidadTextNumber)
                hacerVisible(detallesMultiLine)
            }
        }

        homeButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_favorFragment_to_profileFragment)
        }

        requestFavorButton.setOnClickListener {
            profileViewModel.getProfileData()
            puntos = Integer.parseInt(karmapoints.text.toString())
            if(puntos>=2){
                val auth = FirebaseAuth.getInstance()
                val user = auth.currentUser!!.uid
                val databaseReference = FirebaseDatabase.getInstance().getReference(usersNode).child(user)
                    if(fotsel){
                        type = sacarfotocopias
                        val codigo = codigoTextNumber.text.toString()
                        favorViewModel.createFavor(type,"El código de estudiante es: "+codigo)
                        //profileViewModel.getKarmaData().value
                        currentKarma = Integer.parseInt(karmapoints.text.toString())-2
                        databaseReference.child(karmaNode).setValue(currentKarma)
                        Toast.makeText(this.requireContext(), "Su favor ha sido iniciado", Toast.LENGTH_SHORT).show()
                    }
                    if(compsel){
                        type = comprarkm5
                        val objeto = objetoPlainText.text.toString()
                        val cantidad = cantidadTextNumber.text.toString()
                        favorViewModel.createFavor(type,"Se solicita comprar "+objeto+" x "+cantidad)
                        currentKarma = Integer.parseInt(karmapoints.text.toString())-2
                        databaseReference.child(karmaNode).setValue(currentKarma)
                        Toast.makeText(this.requireContext(), "Su favor ha sido iniciado", Toast.LENGTH_SHORT).show()
                    }
                    if(domsel){
                        type = domiciliop7
                        val details = detallesMultiLine.text.toString()
                        favorViewModel.createFavor(type,details)
                        currentKarma = Integer.parseInt(karmapoints.text.toString())-2
                        databaseReference.child(karmaNode).setValue(currentKarma)
                        Toast.makeText(this.requireContext(), "Su favor ha sido iniciado", Toast.LENGTH_SHORT).show()
                    }
            }else{
                Toast.makeText(this.requireContext(), "Debe de tener 2 o más de karma", Toast.LENGTH_SHORT).show()
            }
        }
    }
}