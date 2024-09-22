package com.example.sustainablesteps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sustainablesteps.adapter.LeaderboardAdapter
import com.example.sustainablesteps.databinding.ActivityLeaderboardsBinding
//import com.example.sustainablesteps.model.LeaderboardModel
import com.example.sustainablesteps.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class Leaderboards : AppCompatActivity() {

    private  val binding: ActivityLeaderboardsBinding by lazy {
        ActivityLeaderboardsBinding.inflate(layoutInflater)
    }

//    private lateinit var LeaderboardAdapter: LeaderboardAdapter
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var leaderboardItems: MutableList<UserModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference

        retrieveLeaderboardItems()

    }

    // implement user details on the leaderboard

    private fun retrieveLeaderboardItems() {

        database = FirebaseDatabase.getInstance()
        val leaderboardRef : DatabaseReference = database.reference.child("user")
        leaderboardItems = mutableListOf()

        leaderboardRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(leaderboardSnapShot in snapshot.children){
                    val leaderboardItem = leaderboardSnapShot.getValue(UserModel::class.java)
                    leaderboardItem?.let {
                        leaderboardItems.add(it)
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
        leaderboardItems.sortByDescending { it.current_points }
        val adapter = LeaderboardAdapter(leaderboardItems, databaseReference , this)
        adapter.notifyDataSetChanged()
        binding.leaderboardsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.leaderboardsRecyclerView.adapter = adapter
    }

}