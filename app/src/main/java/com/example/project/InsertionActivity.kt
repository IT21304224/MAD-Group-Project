package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var itmName : EditText
    private lateinit var itmPrice : EditText
    private lateinit var itmDescription : EditText
    private lateinit var btnSave : Button

    private lateinit var dbRef : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        itmName = findViewById(R.id.enterItem)
        itmPrice = findViewById(R.id.enterPrice)
        itmDescription = findViewById(R.id.enterDescription)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Items")

        btnSave.setOnClickListener{
            saveItemData()
            val intent = Intent(this,Successfull::class.java)
            startActivity(intent)
        }


    }
    private fun saveItemData(){
        val itemName = itmName.text.toString()
        val itemPrice = itmPrice.text.toString()
        val itemDescription = itmDescription.text.toString()

        if (itemName.isEmpty()){
            itmName.error = "Please enter the name"
        }
        if (itemPrice.isEmpty()){
            itmPrice.error = "Please enter the name"
        }
        if (itemDescription.isEmpty()){
            itmDescription.error = "Please enter the name"
        }

        val itemId = dbRef.push().key!!

        val item = ItemModel(itemId,itemName,itemPrice,itemDescription)

        dbRef.child(itemId).setValue(item)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener() { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
        }

    }
