package com.example.signup4

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var user : EditText
    private lateinit var pass : EditText
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        user = findViewById(R.id.username)
        pass = findViewById(R.id.password)

        findViewById<Button>(R.id.loginButton).setOnClickListener(View.OnClickListener {
            val User = user.text.toString()
            val Pass = pass.text.toString()

            if(User.isNotBlank() && Pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(User, Pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            startActivity(Intent(this,feedbacklist::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
            }else{
                Toast.makeText(this,"TextField Empty",Toast.LENGTH_LONG).show()
            }
        })
        findViewById<TextView>(R.id.textView2).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
           // startActivity(Intent())
        }
    }
}