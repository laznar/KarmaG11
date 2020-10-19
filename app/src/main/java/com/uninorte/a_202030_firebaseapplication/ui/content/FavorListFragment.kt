package com.uninorte.a_202030_firebaseapplication.ui.content

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.adapter.FavorsAdapter
import com.uninorte.a_202030_firebaseapplication.adapter.MessagesAdapter
import com.uninorte.a_202030_firebaseapplication.viewmodel.FavorListViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favor_list.*
import kotlinx.android.synthetic.main.fragment_favor_list.view.*
import kotlinx.android.synthetic.main.fragment_messages.*

@AndroidEntryPoint
class FavorListFragment : Fragment(R.layout.fragment_favor_list) {

    val favorListModel: FavorListViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel : FirebaseRealTimeDBViewModel by activityViewModels()
    private val adapter = FavorsAdapter(ArrayList())
    var userUid : String = "_"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favor_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().favor_recycler.adapter = adapter
        requireView().favor_recycler.layoutManager = LinearLayoutManager(requireContext())

        favorListModel.ldFavorList.observe(getViewLifecycleOwner(), Observer { lista ->
            Log.d("MyOut","Número de favores "+lista.size)
            adapter.posts.clear()
            adapter.posts.addAll(lista)
            adapter.notifyDataSetChanged()
            favor_recycler.scrollToPosition(lista.size -1)
        })

        homeButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_favorListFragment_to_profileFragment)
        }

        messagesButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_favorListFragment_to_messagesFragment)
        }
    }
}