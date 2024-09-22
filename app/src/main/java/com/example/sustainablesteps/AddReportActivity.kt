package com.example.sustainablesteps

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sustainablesteps.databinding.ActivityAddReportBinding
import com.example.sustainablesteps.databinding.ActivityLoginBinding
import com.example.sustainablesteps.model.AllReports
import com.example.sustainablesteps.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class AddReportActivity : AppCompatActivity() {

    // Saving report details to database
    private lateinit var reportName: String
    private lateinit var reportDesc: String
    private var reportImgUri: Uri? = null
    private lateinit var reportLocation: String

    //firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase


    private val binding : ActivityAddReportBinding by lazy {
        ActivityAddReportBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // initialise firebase
        auth = FirebaseAuth.getInstance()
        // initialise firebase database instance
        database = FirebaseDatabase.getInstance()

        binding.reportUploadButton.setOnClickListener{
            // get data from fields
            reportName = binding.reportName.text.toString().trim()
            reportDesc = binding.reportDescription.text.toString().trim()
            reportLocation = binding.reportLocation.text.toString().trim()
            if(reportName.isBlank()||reportDesc.isBlank()||reportLocation.isBlank()){
                Toast.makeText(this, "Please fill all the sections", Toast.LENGTH_SHORT).show()
            }else{
                uploadData()
                Toast.makeText(this, "Report Created Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.reportImage.setOnClickListener{
            pickImage.launch("image/*")
        }

    }

    private fun uploadData() {
        //get a reference to the "reports" node in the database
        val reportRef : DatabaseReference = database.getReference("reports")
        // generate a unique key for the new report item
        val newItemKey = reportRef.push().key

        if(reportImgUri!=null){
            val storageRef : StorageReference = FirebaseStorage.getInstance().reference
            val imageRef : StorageReference = storageRef.child("report_images/${newItemKey}.jpg")
            val uploadTask : UploadTask = imageRef.putFile(reportImgUri!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl ->

                    //create new report item
                    val newItem = AllReports(
                        reportName = reportName,
                        reportDesc = reportDesc,
                        reportLocation = reportLocation,
                        reportImage = downloadUrl.toString()
                    )
                    newItemKey?.let{
                        key ->
                        reportRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Data uploaded successfully", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener{
                                Toast.makeText(this, "Data upload Failed", Toast.LENGTH_SHORT).show()

                            }
                    }
                }
            }
                .addOnFailureListener{
                    Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()

                }
        }
        else{
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if(uri != null){
            binding.reportImage.setImageURI(uri)
            reportImgUri = uri
        }
    }
}