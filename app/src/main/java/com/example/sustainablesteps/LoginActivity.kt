package com.example.sustainablesteps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sustainablesteps.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // initialise Firebase Auth
        auth = Firebase.auth
        // initialise Firebase Database
        database = Firebase.database.reference

        binding.dontHaveAnAccount.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener{
            // get text from edittext
            email = binding.emailLogin.text.toString().trim()
            password = binding.passwordLogin.text.toString().trim()

            if(email.isBlank()||password.isBlank()){
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            } else{
                logInUserAccount(email, password)
            }
        }
    }

    private fun logInUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                val user:FirebaseUser? = auth.currentUser
                updateUI(user)
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Authentication Failed, Create Account first", Toast.LENGTH_SHORT).show()
                Log.d("Account", "logInUserAccount : Authentication failed", task.exception)
            }
        }
    }

    // check if user is already logged in but not gonna try it because logging in is cooler.
//    override fun onStart() {
//        super.onStart()
//        val currentUser : FirebaseUser? = auth.currentUser
//        if(currentUser!=null){
//            updateUI(currentUser)
//        }
//    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}