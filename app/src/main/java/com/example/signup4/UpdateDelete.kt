package com.example.signup4

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateDelete : AppCompatActivity() {
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
    private lateinit var Docid: String
    private lateinit var  uid: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        val db = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        uid = auth.currentUser!!.uid
        val intent = Intent()
        val stringExtra = intent.getStringExtra("UserID")

        nameF = findViewById(R.id.Eetname)
        feedBack = findViewById(R.id.Eradiogrup)
        feedBack1 = findViewById(R.id.Eradio1)
        feedBack2 = findViewById(R.id.Eradio2)
        feedBack3 = findViewById(R.id.Eradio3)
        feedBack4 = findViewById(R.id.Eradio4)
        desC = findViewById(R.id.Eetadditfb)
        uEmail = findViewById(R.id.Eetemail)
        uNo = findViewById(R.id.Eetnumber)

        feedBack.setOnCheckedChangeListener{group,  checkedId ->
            if (checkedId == R.id.Eradio1){
                excelend = "Excellent"
            }else if (checkedId == R.id.Eradio2){
                excelend = "Natural"
            }else if(checkedId == R.id.Eradio3){
                excelend = "Good"
            }else if(checkedId == R.id.Eradio4){
                excelend = "Poor"
            }

        }
        findViewById<Button>(R.id.Ebutton3).setOnClickListener(View.OnClickListener {
            // Create a new user with a first and last

            val feedBack5 = mapOf(
                "Full_Name" to nameF.text.toString(),
                "Feed_Back" to excelend,
                "Description" to desC.text.toString(),
                "Email" to uEmail.text.toString(),
                "Phone_No" to uNo.text.toString(),
                "Uid" to uid,
            )
            db.collection("feedBack").document(Docid).update(feedBack5).addOnSuccessListener {
                Toast.makeText(this, "Edit Succcess", Toast.LENGTH_LONG).show()
            }
        })

// Add a new document with a generated ID
        db.collection("feedBack")
            .get()
            .addOnSuccessListener { reslut ->
                for (document in reslut) {
                    val uId = document.data.get("uId").toString()
                    Docid = document.id
                    if (uid == stringExtra) {
                        val fN = document.data.get("Full_Name").toString()
                        val fF = document.data.get("Feed_Back").toString()
                        val fD = document.data.get("Description").toString()
                        val fE = document.data.get("Email").toString()
                        val fP = document.data.get("Phone_No").toString()

                        nameF.setText(fN)

                        if(fF == "Excellent"){
                            feedBack1.isChecked = true
                        }else if(fF == "Netutral"){
                            feedBack2.isChecked = true
                        }else if(fF == "Good"){
                            feedBack3.isChecked = true
                        }else if(fF == "Poor"){
                            feedBack4.isChecked = true
                        }
                        desC.setText(fD)
                        uEmail.setText(fE)
                        uNo.setText(fP)

                    } else {
                       // Toast.makeText(this, "FeedBack Edit", Toast.LENGTH_LONG).show()
                    }


                }
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
                Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
            }

        findViewById<Button>(R.id.Ebtncancle1).setOnClickListener(View.OnClickListener {
            db.collection("feedBack").document(Docid).delete().addOnSuccessListener {
                Toast.makeText(this,"Delete Success", Toast.LENGTH_LONG).show()
            }
        })

    }
}