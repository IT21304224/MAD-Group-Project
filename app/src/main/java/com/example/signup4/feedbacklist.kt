package com.example.signup4


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class feedbacklist : AppCompatActivity() {

    private  lateinit var recyclerView : RecyclerView
    private lateinit var arrayfeedbaklist : ArrayList<feedbackclass>
    private  var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedbacklist)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        arrayfeedbaklist = arrayListOf()



        db = FirebaseFirestore.getInstance()

        db.collection("feedBack").get().addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                         val feedB : feedbackclass? = data.toObject(feedbackclass::class.java)
                        if(feedB != null){
                            arrayfeedbaklist.add(feedB)
                        }

                    }
                    recyclerView.adapter = Myadapter(arrayfeedbaklist)
                }
        }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
            }

        findViewById<Button>(R.id.btnaddFeed).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_feedback1::class.java))
        })
    }
}