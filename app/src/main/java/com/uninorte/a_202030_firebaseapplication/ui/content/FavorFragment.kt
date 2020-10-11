package com.uninorte.a_202030_firebaseapplication.ui.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.navigation.findNavController
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.utils.hacerInvisible
import com.uninorte.a_202030_firebaseapplication.utils.hacerVisible
import kotlinx.android.synthetic.main.fragment_favor.*


class FavorFragment : Fragment(R.layout.fragment_favor) {


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
        val radioGroup = (R.id.radiogroupButton) as RadioGroup




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

        requestFavorButton.setOnClickListener{
            //codigoTextNumber.setVisibility(View.INVISIBLE)
            //objetoPlainText.setVisibility(View.INVISIBLE)
            //cantidadTextNumber.setVisibility(View.INVISIBLE)
            //editTextTextMultiLine.setVisibility(View.INVISIBLE)
        }

    }
}