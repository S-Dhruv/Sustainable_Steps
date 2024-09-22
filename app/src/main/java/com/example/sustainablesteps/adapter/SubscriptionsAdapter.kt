package com.example.sustainablesteps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sustainablesteps.databinding.SubscriptionsItemBinding

class SubscriptionsAdapter(private val SubscriptionNames : ArrayList<String>, private val SubscriptionDescription: ArrayList<String>,
                            private val SubscriptionPictures: ArrayList<Int>) :
    RecyclerView.Adapter<SubscriptionsAdapter.SubscriptionsViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionsViewHolder {
        val binding = SubscriptionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubscriptionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionsViewHolder, position: Int) {
        holder.bind(SubscriptionNames[position], SubscriptionDescription[position], SubscriptionPictures[position])
    }

    override fun getItemCount(): Int = SubscriptionNames.size

    class SubscriptionsViewHolder(private val binding: SubscriptionsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, desc: String, img: Int) {
            binding.subscriptionName.text = name
            binding.subsriptionDescription.text = desc
            binding.subscriptionImage.setImageResource(img)
        }

    }
}