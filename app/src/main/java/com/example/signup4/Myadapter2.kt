package com.example.signup4

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.play.integrity.internal.c
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Myadapter2(private val feedbacklisAd:ArrayList<feedbackclass>) : RecyclerView.Adapter<Myadapter2.MyViewHolder>() {

private lateinit var mListener : onItemclicklisntor



    interface onItemclicklisntor {

        fun onItemClick(position: Int)
    }
    fun satOnItemClickListener(listener : onItemclicklisntor){
        mListener = listener
    }



    class MyViewHolder(itemView:  View, listener: onItemclicklisntor):RecyclerView.ViewHolder(itemView){
        val feed1 : RadioButton = itemView.findViewById(R.id.radio3)
        val feed2 : RadioButton = itemView.findViewById(R.id.radio4)
        val feed3 : RadioButton = itemView.findViewById(R.id.radio5)
        val feed4 : RadioButton = itemView.findViewById(R.id.radio6)
        val feedName : TextView = itemView.findViewById(R.id.name1)
        val feedcomment : TextView = itemView.findViewById(R.id.comment1)



        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.perstion_list,parent,false)
        return  MyViewHolder(itemView,mListener)
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

        /* holder.deleteFeed.setOnClickListener {
             val firestore = Firebase.firestore
             firestore.collection("feedBack").get().addOnSuccessListener { result ->
                 for(document in result){
                     Log.d(TAG, "${document.id} => ${document.data}")
                     val id = document.id
                     val get = document.data.get("Full_Name")
                     if(holder.feed1.text.toString() == get){
                         firestore.collection("feedBack").document(id).delete().addOnSuccessListener {

                         }
                     }
                 }

             }
         }*/

    }

    override fun getItemCount(): Int {
        return feedbacklisAd.size
    }
}