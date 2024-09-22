package com.example.sustainablesteps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sustainablesteps.adapter.ReportsAdapter
import com.example.sustainablesteps.databinding.ActivityViewReportBinding
import com.example.sustainablesteps.model.AllReports
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewReportActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var reportItems: ArrayList<AllReports> = ArrayList()

    private val binding : ActivityViewReportBinding by lazy {
        ActivityViewReportBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        retrieveReportItem()


    }

    private fun retrieveReportItem() {
        database = FirebaseDatabase.getInstance()
        val reportRef: DatabaseReference = database.reference.child("reports")

        // fetching data from database
        reportRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear existing data before populating
                reportItems.clear()

                // loop through each food item using
                for (reportSnapShot in snapshot.children){
                    val reportItem = reportSnapShot.getValue(AllReports::class.java)
                    reportItem?.let {
                        reportItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError", "Error: ${error.message}")
            }
        })
    }

    private fun setAdapter() {
        val adapter = ReportsAdapter(this@ViewReportActivity, reportItems, databaseReference)
        binding.viewReportsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.viewReportsRecyclerView.adapter = adapter
    }
}