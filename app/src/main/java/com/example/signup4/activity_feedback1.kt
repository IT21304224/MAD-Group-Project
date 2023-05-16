package com.example.signup4

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class activity_feedback1 : AppCompatActivity() {

    private lateinit var nameF : EditText
    private lateinit var feedBack : RadioGroup
    private lateinit var feedBack1 : RadioButton
    private lateinit var feedBack2 : RadioButton
    private lateinit var feedBack3 : RadioButton
    private lateinit var feedBack4 : RadioButton
    private lateinit var desC : EditText
    private lateinit var uEmail : EditText
    private lateinit var uNo : EditText
    private lateinit var excelend : String

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback1)

        val db = Firebase.firestore
        auth = Firebase.auth

        nameF = findViewById(R.id.etname)
        feedBack = findViewById(R.id.radiogrup)
        feedBack1 = findViewById(R.id.radio1)
        feedBack2 = findViewById(R.id.radio2)
        feedBack3 = findViewById(R.id.radio3)
        feedBack4 = findViewById(R.id.radio4)
        desC = findViewById(R.id.etadditfb)
        uEmail = findViewById(R.id.etemail)
        uNo = findViewById(R.id.etnumber)

        feedBack.setOnCheckedChangeListener{group,  checkedId ->
            if (checkedId == R.id.radio1){
                 excelend = "Excellent"
            }else if (checkedId == R.id.radio2){
                excelend = "Natural"
            }else if(checkedId == R.id.radio3){
                excelend = "Good"
            }else if(checkedId == R.id.radio4){
                excelend = "Poor"
            }

        }



        findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            // Create a new user with a first and last
            val uid = auth.currentUser!!.uid
            val feedBack = hashMapOf(
                "Full_Name" to nameF.text.toString(),
                "Feed_Back" to excelend,
                "Description" to desC.text.toString(),
                "Email" to uEmail.text.toString(),
                "Phone_No" to uNo.text.toString(),
                "Uid" to uid,


            )

// Add a new document with a generated ID
            db.collection("feedBack")
                .add(feedBack)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this,"Thanks for your feedback",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
                }
        })
        findViewById<Button>(R.id.btncancle1).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_userprofile::class.java))
        })
        findViewById<ImageView>(R.id.imageView2).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_feedback3::class.java))
        })
        findViewById<ImageView>(R.id.imageView11).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,activity_userprofile::class.java))
        })
    }
}