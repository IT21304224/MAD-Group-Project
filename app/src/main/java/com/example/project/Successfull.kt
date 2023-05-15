package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Successfull : AppCompatActivity() {

    private lateinit var btnInsert : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successfull)

        btnInsert = findViewById(R.id.buttonDone)

        btnInsert.setOnClickListener{
            val intent = Intent(this,FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}