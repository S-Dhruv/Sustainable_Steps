package com.example.sustainablesteps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sustainablesteps.databinding.ActivitySignUpBinding
import com.example.sustainablesteps.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {


    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var userName : String
    private lateinit var password : String
    private lateinit var database : DatabaseReference

    private val binding : ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialisation Firebase Auth
        auth = Firebase.auth
        database = Firebase.database.reference

        binding.alreadyhaveanacc.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.saveAndNextButton.setOnClickListener{
            //get text from edit text
            userName = binding.userNameSignin.text.toString().trim()
            email = binding.emailSignin.text.toString().trim()
            password = binding.passwordSignin.text.toString().trim()

            if(userName.isBlank()||email.isBlank()||password.isBlank())
            {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email, password)
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Account created Successfully", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }
        }
    }

    // save data to database
    private fun saveUserData() {
        userName = binding.userNameSignin.text.toString().trim()
        email = binding.emailSignin.text.toString().trim()
        password = binding.passwordSignin.text.toString().trim()
        val user = UserModel(userName, email, password, 0)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        // save user data into Firebase Database
        database.child("user").child(userId).setValue(user)

    }
}