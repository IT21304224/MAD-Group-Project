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
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class activity_userprofile : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var firstName: EditText
    private lateinit var lsatName: EditText
    private lateinit var mobileNo: EditText
    private lateinit var proImage: ImageView
    private lateinit var uid: String

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)

        auth = Firebase.auth
        uid = auth.currentUser!!.uid

        userName = findViewById(R.id.user1)
        email = findViewById(R.id.email1)
        firstName = findViewById(R.id.fname)
        lsatName = findViewById(R.id.lname)
        mobileNo = findViewById(R.id.contect)
        proImage = findViewById(R.id.imageView)


        db.collection("user")
            .get()
            .addOnSuccessListener { reslut ->
                for (document in reslut) {
                    if (firstName.text.toString().isEmpty()) {
                        val uId = document.data.get("userId").toString()

                        if (uid == uId) {
                            val uName = document.data.get("FUll NAme").toString()
                            val eMail = document.data.get("Email").toString()


                            userName.setText(uName)
                            email.setText(eMail)
                        } else {

                        }
                    }


                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        db.collection("UserEdit")
            .get()
            .addOnSuccessListener { reslut ->
                for (document in reslut) {
                    val uId = document.data.get("uId").toString()
                    if (uid == uId) {
                        val fN = document.data.get("First Name").toString()
                        val lN = document.data.get("Last Name").toString()
                        val mN = document.data.get("Mobbile No").toString()

                        firstName.setText(fN)
                        lsatName.setText(lN)
                        mobileNo.setText(mN)

                    } else {
                        //Toast.makeText(this, "Update Profile", Toast.LENGTH_LONG).show()
                    }


                }


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        findViewById<Button>(R.id.Edit).setOnClickListener(View.OnClickListener {
            val fName = firstName.text.toString()
            val lName = lsatName.text.toString();
            val mNo = mobileNo.text.toString()
            val uid1 = auth.currentUser!!.uid
            val user2 = mapOf(
                "First Name" to fName,
                "Last Name" to lName,
                "Mobbile No" to mNo,
                "uId" to uid1,
            )
            db.collection("UserEdit").add(user2).addOnSuccessListener {
                Toast.makeText(this, "Update Profile", Toast.LENGTH_LONG).show()
            }
        })
        findViewById<Button>(R.id.signout).setOnClickListener(View.OnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        })
        findViewById<Button>(R.id.Feedback).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, activity_feedback3::class.java))
        })
    }

    override fun onStart() {
        super.onStart()
    }
}