package com.example.sustainablesteps.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sustainablesteps.databinding.ReportItemBinding
import com.example.sustainablesteps.model.AllReports
import android.content.Context
import com.google.firebase.database.DatabaseReference

class ReportsAdapter(
    private val context: Context,
    private val reportList: ArrayList<AllReports>,
    databaseReference: DatabaseReference
) : RecyclerView.Adapter<ReportsAdapter.ReportsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportsAdapter.ReportsViewHolder {
        val binding = ReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsAdapter.ReportsViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int = reportList.size
    inner class ReportsViewHolder(private val binding: ReportItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            val reportItem = reportList[position]
            val uriString = reportItem.reportImage
            val uri = Uri.parse(uriString)
            binding.sampleReportName.text = reportItem.reportName
            Glide.with(context).load(uri).into(binding.sampleReportImage)

        }

    }

}