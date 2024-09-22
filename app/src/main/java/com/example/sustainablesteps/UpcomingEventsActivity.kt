package com.example.sustainablesteps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sustainablesteps.adapter.EventsAdapter
import com.example.sustainablesteps.adapter.SubscriptionsAdapter
import com.example.sustainablesteps.databinding.ActivitySubscriptionsBinding
import com.example.sustainablesteps.databinding.ActivityUpcomingEventsBinding

class UpcomingEventsActivity : AppCompatActivity() {

    private  val binding: ActivityUpcomingEventsBinding by lazy {
        ActivityUpcomingEventsBinding.inflate(layoutInflater)
    }
    private lateinit var eventsAdapter: EventsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val eventName = listOf("Plogging", "Waste Reduction Workshop", "Recyling Drive", "Lake Cleanup","Plogging", "Waste Reduction Workshop", "Recyling Drive", "Lake Cleanup" )
        val eventDisc = listOf("Organised by-", "Organised by-", "Organised by-", "Organised by-", "Organised by-", "Organised by-", "Organised by-", "Organised by-")
        val eventImage = listOf(
            R.drawable.plogging_icon,
            R.drawable.wasteworkshop,
            R.drawable.recyclingdrive,
            R.drawable.lakecleanup,
            R.drawable.plogging_icon,
            R.drawable.wasteworkshop,
            R.drawable.recyclingdrive,
            R.drawable.lakecleanup
        )

        val adapter = EventsAdapter(
            ArrayList(eventName),
            ArrayList(eventDisc),
            ArrayList(eventImage)
        )
        binding.upcomingRecylcerView.layoutManager = LinearLayoutManager(this)
        binding.upcomingRecylcerView.adapter = adapter
    }
}