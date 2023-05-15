package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectionActivity : AppCompatActivity() {

    private lateinit var btnInsert :Button
    private lateinit var btnFetch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        btnInsert = findViewById(R.id.btnInsertData)
        btnFetch = findViewById(R.id.btnFetchData)

        btnInsert.setOnClickListener{
            val intent = Intent(this,InsertionActivity::class.java)
            startActivity(intent)
        }
        btnFetch.setOnClickListener{
            val intent = Intent(this,FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}