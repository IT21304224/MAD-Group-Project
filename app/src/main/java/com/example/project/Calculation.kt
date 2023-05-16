package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class Calculation : AppCompatActivity() {

    private lateinit var etItemAmount: EditText
    private lateinit var etDeliveryFee: TextView
    private lateinit var etTotAmount: TextView
    private lateinit var btnPayok: Button
    private lateinit var btnCancel: Button
    private lateinit var btncalc:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        etItemAmount = findViewById(R.id.etItemAmount)
        etDeliveryFee = findViewById(R.id.etDeliveryFee)
        etTotAmount = findViewById(R.id.etTotAmount)
        btnPayok = findViewById(R.id.btnPayok)
        btnCancel = findViewById(R.id.btnCancel)
        btncalc = findViewById(R.id.btncalc)

        var delfee = 10

        btncalc.setOnClickListener {
            val res1 = etItemAmount.text.toString().toInt()


            if (res1 > 2000) {
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("You got a offer")
                    .setMessage("Since Your Amount is over 2000 Rs you got 100 Rs offer .")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()

                    }
                    .create()

                alertDialog.show()
                delfee = 100
            }else{
                delfee = 0
            }

            etDeliveryFee.text = delfee.toString()
            val res2 = etDeliveryFee.text.toString().toInt()
            val result = res1-res2
            etTotAmount.text = result.toString()
        }
    }



}