package com.example.signup4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class activity_feedback3 : AppCompatActivity() {
    private  lateinit var recyclerView2 : RecyclerView
    private lateinit var arrayfeedbaklist : ArrayList<feedbackclass>
    private  var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback3)

        auth = Firebase.auth
        val uid = auth.currentUser!!.uid

        recyclerView2 = findViewById(R.id.recyclerView12)
        recyclerView2.layoutManager = LinearLayoutManager(this)

        arrayfeedbaklist = arrayListOf()



        db = FirebaseFirestore.getInstance()

        db.collection("feedBack").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    val userId = data.get("Uid").toString()
                    if(userId == uid){
                        val feedB : feedbackclass? = data.toObject(feedbackclass::class.java)
                        if(feedB != null){
                            arrayfeedbaklist.add(feedB)
                        }
                    }
                }
                var adaptera = Myadapter2(arrayfeedbaklist)
                recyclerView2.adapter = adaptera
                adaptera.satOnItemClickListener(object : Myadapter2.onItemclicklisntor{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@activity_feedback3,UpdateDelete::class.java)
                        intent.putExtra("UserID",uid)
                        startActivity(intent)
                      //startActivity(Intent(this@activity_feedback3,UpdateDelete::class.java))
                    }

                })
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }

        findViewById<ImageView>(R.id.imageView2).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_userprofile::class.java))
        })
        findViewById<ImageView>(R.id.imageView11).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_userprofile::class.java))
        })
    }




}