package com.example.sustainablesteps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sustainablesteps.Leaderboards
import com.example.sustainablesteps.databinding.LeaderboardItemBinding
import com.example.sustainablesteps.model.UserModel
import com.google.firebase.database.DatabaseReference

class LeaderboardAdapter(private val leaderboardItems: List<UserModel>,
                         databaseReference: DatabaseReference,
                         private val context: Leaderboards
) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = LeaderboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaderboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = leaderboardItems.size

    inner class LeaderboardViewHolder(private val binding: LeaderboardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            val leaderboardItem = leaderboardItems[position]

            binding.apply{
                leaderboardsusername.text = leaderboardItem.name
                leaderboardpointsnumber.text = leaderboardItem.current_points.toString() + " points"
//                profileLeaderboards.setImageResource(profilePic)
                val rank = (position+1).toString()
                val level = leaderboardItem.current_points/20 + 1
                leaderboardslevel.text = "Level " + level.toString()
                leaderboardusrrank.text = "$rank"
            }

        }

    }
}