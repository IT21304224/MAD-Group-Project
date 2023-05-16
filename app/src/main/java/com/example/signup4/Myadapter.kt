package com.example.signup4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter(private val feedbacklisAd:ArrayList<feedbackclass>) : RecyclerView.Adapter<Myadapter.MyViewHolder>() {
    class MyViewHolder(itemView:  View):RecyclerView.ViewHolder(itemView){
        val feed1 : RadioButton = itemView.findViewById(R.id.radio3)
        val feed2 : RadioButton = itemView.findViewById(R.id.radio4)
        val feed3 : RadioButton = itemView.findViewById(R.id.radio5)
        val feed4 : RadioButton = itemView.findViewById(R.id.radio6)
        val feedName : TextView = itemView.findViewById(R.id.name1)
        val feedcomment : TextView = itemView.findViewById(R.id.comment1)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.feedName.text = feedbacklisAd[position].Full_Name
        holder.feedcomment.text = feedbacklisAd[position].Description
        val feedBack = feedbacklisAd[position].Feed_Back
        if(feedBack == "Excellent"){
            holder.feed1.isChecked = true
        }else if(feedBack == "Netutral"){
            holder.feed2.isChecked = true
        }else if(feedBack == "Good"){
            holder.feed3.isChecked = true
        }else if(feedBack == "Poor"){
            holder.feed4.isChecked = true
        }

        holder.feed1.isClickable = false
        holder.feed2.isClickable = false
        holder.feed3.isClickable = false
        holder.feed4.isClickable = false



    }

    override fun getItemCount(): Int {
       return feedbacklisAd.size
    }
}