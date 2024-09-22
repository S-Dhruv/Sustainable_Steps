package com.example.sustainablesteps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sustainablesteps.adapter.LeaderboardAdapter
import com.example.sustainablesteps.adapter.SubscriptionsAdapter
import com.example.sustainablesteps.databinding.ActivityLeaderboardsBinding
import com.example.sustainablesteps.databinding.ActivitySubscriptionsBinding

class SubscriptionsActivity : AppCompatActivity() {
    private  val binding: ActivitySubscriptionsBinding by lazy {
        ActivitySubscriptionsBinding.inflate(layoutInflater)
    }
    private lateinit var subscriptionsAdapter: SubscriptionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val subscriptionName = listOf("Netflix", "Amazon Prime", "CultFit", "Spotify", "Zomato","Netflix", "Amazon Prime", "CultFit", "Spotify", "Zomato" )
        val subscriptionDisc = listOf("1 month subscription", "3 months subscription", "1 month subscription", "1 month subscription", "1 month subscription", "1 month subscription", "3 months subscription", "1 month subscription", "1 month subscription", "1 month subscription")
        val subscriptionImage = listOf(
            R.drawable.netflixsubscription,
            R.drawable.amazonsubscription,
            R.drawable.cultfitsubscription,
            R.drawable.spotifysubscription,
            R.drawable.zomatosubscription,
            R.drawable.netflixsubscription,
            R.drawable.amazonsubscription,
            R.drawable.cultfitsubscription,
            R.drawable.spotifysubscription,
            R.drawable.zomatosubscription,
        )

        val adapter = SubscriptionsAdapter(
            ArrayList(subscriptionName),
            ArrayList(subscriptionDisc),
            ArrayList(subscriptionImage)
        )
        binding.subscriptionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.subscriptionsRecyclerView.adapter = adapter
    }
}