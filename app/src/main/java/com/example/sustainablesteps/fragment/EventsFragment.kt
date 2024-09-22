package com.example.sustainablesteps.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sustainablesteps.CreateEventActivity
import com.example.sustainablesteps.Leaderboards
import com.example.sustainablesteps.R
import com.example.sustainablesteps.SubscriptionsActivity
import com.example.sustainablesteps.UpcomingEventsActivity
import com.example.sustainablesteps.databinding.FragmentEventsBinding
import com.example.sustainablesteps.databinding.FragmentPointsBinding

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.joinEventCard.setOnClickListener {
            val intent = Intent(requireContext(), UpcomingEventsActivity::class.java)
            startActivity(intent)
        }
        binding.createEventCard.setOnClickListener {
            val intent = Intent(requireContext(), CreateEventActivity::class.java)
            startActivity(intent)
        }
    }
}