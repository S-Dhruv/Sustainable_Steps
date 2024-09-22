package com.example.sustainablesteps

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sustainablesteps.databinding.ActivityCreateEventBinding
import com.example.sustainablesteps.databinding.ActivityUpcomingEventsBinding
import com.example.sustainablesteps.model.EventModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View
import com.google.firebase.storage.FirebaseStorage

class CreateEventActivity : AppCompatActivity() {

    // event details
    private lateinit var eventName: String
    private lateinit var eventType: String
    private lateinit var eventHost: String
    private lateinit var eventExpectedNum: String
    private lateinit var eventDate: String
    private lateinit var eventTime: String
    private lateinit var eventVenue: String
    private lateinit var eventDescription: String
    private var eventImageUri: Uri? = null

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    private  val binding: ActivityCreateEventBinding by lazy {
        ActivityCreateEventBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // initialise Firebase
        auth = FirebaseAuth.getInstance()
        // initialise Firebase Database Instance
        database = FirebaseDatabase.getInstance()


        ArrayAdapter.createFromResource(
            this,
            R.array.event_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.eventTypeSpinner.adapter = adapter
        }

        binding.datePickButton.setOnClickListener {
            openDatePicker()
        }

        binding.timePickButton.setOnClickListener{
            openTimePicker()
        }
        binding.createEventButton.setOnClickListener{
            // get data from fields
            eventName = binding.createEventName.text.toString().trim()
            eventType = binding.createEventType.text.toString().trim()
//            eventHost = binding.eventTypeSpinner.text.toString().trim()
            eventExpectedNum = binding.expectedNumberOfPeople.text.toString().trim()
            eventDate = binding.showDate.text.toString().trim()
            eventTime = binding.showTime.text.toString().trim()
            eventVenue = binding.eventVenue.text.toString().trim()
            eventDescription = binding.eventDescription.text.toString().trim()
//            eventImageUri =

            if(eventName.isBlank()||eventType.isBlank()||eventExpectedNum.isBlank()||eventDate.isBlank()||eventTime.isBlank()||eventVenue.isBlank()||eventDescription.isBlank())
            {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                uploadData()
                Toast.makeText(this, "Event created successfully", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun uploadData() {
        // Getting a reference to the "events" node in the database
        val eventRef = database.getReference("events")
        // Generate a unique key for the new event
        val newItemKey = eventRef.push().key

        val storageRef = FirebaseStorage.getInstance().reference
//        val imageRef = storageRef.child("eve")
        val newItem = EventModel(
            eventName = eventName,
            eventType = eventType,
            eventHost = null,
            eventExpectedNum = eventExpectedNum,
            eventDate = eventDate,
            eventTime = eventTime,
            eventVenue = eventVenue,
            eventDescription = eventDescription,
            eventImage = null
        )
        newItemKey?.let {
            key ->
            eventRef.child(key).setValue(newItem).addOnSuccessListener {
                Toast.makeText(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener{
                    Toast.makeText(this, "Data Upload Failed", Toast.LENGTH_SHORT).show()

                }
        }
    }

    private fun openTimePicker() {
        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                // Showing the picked value in the textView
                binding.showTime.text = "$hour:$minute"
            },
            15,
            30,
            false
        )

        timePickerDialog.show()
    }

    private fun openDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            null,
            2023,
            0,
            20
        )

        datePickerDialog.setOnDateSetListener { _, year, month, day ->
            // Showing the picked value in the textView
            binding.showDate.text = "$year.${month + 1}.$day"
        }

        datePickerDialog.show()
    }
}