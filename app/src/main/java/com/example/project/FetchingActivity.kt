package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class FetchingActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var itmRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var searchView: SearchView
    private lateinit var itmList : ArrayList<ItemModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        itmRecyclerView = findViewById(R.id.tvEmpName)
        itmRecyclerView.layoutManager = LinearLayoutManager(this)
        itmRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchView = findViewById(R.id.searchView)

        itmList = arrayListOf<ItemModel>()

        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search for classes"

        getItemsData()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

   override fun onQueryTextChange(newText: String?): Boolean {
        if (!TextUtils.isEmpty(newText)) {
            search(newText!!)
        } else {
            getItemsData()
        }
        return true
    }

    private fun search(query: String) {
        val searchResultList = arrayListOf<ItemModel>()
        for (ItemModel in itmList) {
            if (ItemModel.itemName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase()) == true) {
                searchResultList.add(ItemModel)
            }
        }

        val cAdapter = ItmAdapter(searchResultList)
        itmRecyclerView.adapter = cAdapter
        cAdapter.setOnItemClickListener(object : ItmAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@FetchingActivity, ItemDetailsActivity::class.java)
                //put extras
                intent.putExtra("itemId", searchResultList[position].itemId)
                intent.putExtra("itemName", searchResultList[position].itemName)
                intent.putExtra("itemPrice", searchResultList[position].itemPrice)
                intent.putExtra("itemDescription", searchResultList[position].itemDescription)

                startActivity(intent)
            }
        })
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

private fun SearchView.setOnQueryTextListener(fetchingActivity: FetchingActivity) {

}
