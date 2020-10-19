package com.uninorte.a_202030_firebaseapplication.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.model.Favor
import kotlinx.android.synthetic.main.list_item_favor.view.*


class FavorsAdapter(val posts: ArrayList<Favor>): RecyclerView.Adapter<FavorsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_favor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favor: Favor) {
            itemView.uid.text = favor.uid
            itemView.type.text = favor.type
            itemView.status.text = favor.status
            itemView.details.text = favor.details
            itemView.setBackgroundColor(Color.GREEN)
            itemView.uid.gravity = Gravity.RIGHT
            itemView.type.gravity = Gravity.RIGHT
            itemView.status.gravity = Gravity.RIGHT
            itemView.details.gravity = Gravity.RIGHT
        }
    }

}