package com.example.signup4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class activity_feedback2 : AppCompatActivity() {

    private lateinit var fName1 : EditText
    private lateinit var fDes1 : EditText
    private lateinit var fName2 : EditText
    private lateinit var fDes2 : EditText
    private lateinit var chack1 : Switch
    private lateinit var chack2 : Switch

    lateinit var idD: String

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback2)


        fName1 = findViewById(R.id.textView114)
        fDes1 = findViewById(R.id.textView24)
        fName2 = findViewById(R.id.textView14)
        fDes2 = findViewById(R.id.textView2)
        chack1 = findViewById(R.id.switch1)
        chack2 = findViewById(R.id.switch2)


        if(chack1.isChecked){
            val chek = "Excellent"
        }else{
            val chek = "Bad"
        }
        if(chack2.isChecked){
            val chek = "Excellent"
        }else{
            val chek = "Bad"
        }

        db.collection("feedBack")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    idD = document.id
                    val sN1 = document.data.get("Full Name").toString()
                    val sD1 = document.data.get("Description").toString()
                    val cK = document.data.get("Feed Back").toString()
                    if(cK == "Excellent"){
                        chack1.isClickable = true
                    }else{
                        chack1.isClickable = false
                    }
                    fName1.setText(sN1)
                    fDes1.setText(sD1)

                    fName2.setText("Jasimith Amila Karuwala")
                    fDes2.setText("hgfksd jdfkdvsjf fgdsu gduifs gdsufisdofgsfui gf gdufisdo gdf gufisdo fgdfusidfp gfsuid f gdfuisdp ugdf gfuisd gdufispd gfuisdfp udgfuisdfpsd gdufispd fgdfuisdpf dgfsi")

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        findViewById<Button>(R.id.button92).setOnClickListener(View.OnClickListener {
            db.collection("feedBack").document(idD).delete().addOnSuccessListener {
                Toast.makeText(this,"Delete success",Toast.LENGTH_LONG).show()
            }
        })

        findViewById<Button>(R.id.button92).setOnClickListener(View.OnClickListener {
            val upd = mapOf(
                "Full Name" to fName1.text.toString(),
                "Description" to fDes1.text.toString(),
            )
            db.collection("feedBack").document(idD).update(upd).addOnSuccessListener {
                Toast.makeText(this,"Update success",Toast.LENGTH_LONG).show()
            }
        })
    }
}