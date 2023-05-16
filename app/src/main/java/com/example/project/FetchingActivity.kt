package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var itmRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var itmList : ArrayList<ItemModel>
    private lateinit var dbRef : DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)



        itmRecyclerView = findViewById(R.id.rvEmp)
        itmRecyclerView.layoutManager = LinearLayoutManager(this)
        itmRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        itmList = arrayListOf<ItemModel>()



        getItemsData()
    }
    private fun getItemsData(){
        itmRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Items")

        dbRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itmList.clear()
                if(snapshot.exists()){
                    for(itmSnap in snapshot.children){
                       val itmData = itmSnap.getValue(ItemModel::class.java)
                        itmList.add(itmData!!)
                    }
                    val iAdapter = ItmAdapter(itmList)
                    itmRecyclerView.adapter = iAdapter

                    iAdapter.setOnItemClickListener(object :ItmAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity,ItemDetailsActivity::class.java)

                            intent.putExtra("itemId",itmList[position].itemId)
                            intent.putExtra("itemName",itmList[position].itemName)
                            intent.putExtra("itemPrice",itmList[position].itemPrice)
                            intent.putExtra("itemDescription",itmList[position].itemDescription)
                            startActivity(intent)
                        }


                    })

                    itmRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
}