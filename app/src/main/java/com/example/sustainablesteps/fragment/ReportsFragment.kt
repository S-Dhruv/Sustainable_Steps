package com.example.sustainablesteps.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sustainablesteps.AddReportActivity
import com.example.sustainablesteps.R
import com.example.sustainablesteps.ViewReportActivity
import com.example.sustainablesteps.databinding.FragmentPointsBinding
import com.example.sustainablesteps.databinding.FragmentReportsBinding

class ReportsFragment : Fragment() {
    private lateinit var binding: FragmentReportsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createReportButton.setOnClickListener{
            startActivity(Intent(requireContext(), AddReportActivity::class.java))
        }
        binding.viewReportsButton.setOnClickListener{
            startActivity(Intent(requireContext(), ViewReportActivity::class.java))
        }

    }
    companion object {

    }
}