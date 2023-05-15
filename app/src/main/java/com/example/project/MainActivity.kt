package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnh : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnh = findViewById(R.id.ivHstore)

        btnh.setOnClickListener{
            val intent = Intent(this,SelectionActivity::class.java)
            startActivity(intent)
        }

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}