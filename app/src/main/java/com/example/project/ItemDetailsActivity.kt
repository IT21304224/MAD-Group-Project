package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var tvItmId:TextView
    private lateinit var tvItmName:TextView
    private lateinit var tvItmPrice:TextView
    private lateinit var tvItmDescription:TextView
    private lateinit var buy:Button
    private lateinit var editButton1:Button
    private lateinit var deletebutton:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)





        initView()
        setValuesToViews()

        editButton1.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("itemId").toString(),
                intent.getStringExtra("itemName").toString()

            )
        }

        deletebutton.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("itemId").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Items").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Item data deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this,"Error ${error.message}",Toast.LENGTH_LONG).show()

        }
    }

    private fun initView() {
        tvItmId = findViewById(R.id.tvItmId)
        tvItmName = findViewById(R.id.tvItemName)
        tvItmPrice = findViewById(R.id.tvItemPrice)
        tvItmDescription = findViewById(R.id.tvItemDescription)
        deletebutton = findViewById(R.id.deletebutton)
        editButton1 = findViewById(R.id.editButton1)
    }

    private fun setValuesToViews(){
        tvItmId.text = intent.getStringExtra("itemId")
        tvItmName.text = intent.getStringExtra("itemName")
        tvItmPrice.text = intent.getStringExtra("itemPrice")
        tvItmDescription.text = intent.getStringExtra("itemDescription")
    }

    private fun openUpdateDialog(
        itemId:String,
        itemName:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val editName = mDialogView.findViewById<EditText>(R.id.editName)
        val editPrice = mDialogView.findViewById<EditText>(R.id.editPrice)
        val editDescription = mDialogView.findViewById<EditText>(R.id.editDescription)
        val saveChanges = mDialogView.findViewById<Button>(R.id.saveChanges)

        editName.setText(intent.getStringExtra("itemName").toString())
        editPrice.setText(intent.getStringExtra("itePrice").toString())
        editDescription.setText(intent.getStringExtra("itemDescription").toString())

        mDialog.setTitle("Updating $itemName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        saveChanges.setOnClickListener{
            updateItmData(
                itemId,
                editName.text.toString(),
                editPrice.text.toString(),
                editDescription.text.toString()
            )

            Toast.makeText(applicationContext,"Item data updated",Toast.LENGTH_LONG).show()

            tvItmName.text = editName.text.toString()
            tvItmPrice.text = editPrice.text.toString()
            tvItmDescription.text = editDescription.text.toString()

            alertDialog.dismiss()
        }

    }
      private fun updateItmData(
          id :String,
          name:String,
          price:String,
          description:String
      ){
          val dbRef = FirebaseDatabase.getInstance().getReference("Items").child(id)
          val itmInfo = ItemModel(id,name,price,description)
          dbRef.setValue(itmInfo)
    }
}