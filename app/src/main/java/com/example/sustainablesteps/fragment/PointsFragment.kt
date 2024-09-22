package com.example.sustainablesteps.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sustainablesteps.Leaderboards
import com.example.sustainablesteps.R
import com.example.sustainablesteps.SubscriptionsActivity
import com.example.sustainablesteps.databinding.FragmentPointsBinding


class PointsFragment : Fragment() {

    private lateinit var binding: FragmentPointsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPointsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.leaderboardCard.setOnClickListener{
            val intent = Intent(requireContext(), Leaderboards::class.java)
            startActivity(intent)
        }
        binding.subscriptionCard.setOnClickListener{
            val intent = Intent(requireContext(), SubscriptionsActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {

    }
}