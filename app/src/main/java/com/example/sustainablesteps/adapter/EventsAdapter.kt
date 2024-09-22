package com.example.sustainablesteps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sustainablesteps.databinding.EventsItemBinding
import com.example.sustainablesteps.databinding.SubscriptionsItemBinding

class EventsAdapter(private val EventNames : ArrayList<String>, private val EventDescription: ArrayList<String>,
                    private val EventPictures: ArrayList<Int>) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.EventsViewHolder {
        val binding = EventsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsAdapter.EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventsViewHolder, position: Int) {
        holder.bind(EventNames[position], EventDescription[position], EventPictures[position])

    }

    override fun getItemCount(): Int = EventNames.size

    class EventsViewHolder(private val binding: EventsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, disc: String, img: Int) {
            binding.eventName.text = name
            binding.eventDisc.text = disc
            binding.eventImg.setImageResource(img)
        }

    }

}