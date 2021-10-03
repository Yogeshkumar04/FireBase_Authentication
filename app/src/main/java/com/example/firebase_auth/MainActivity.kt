package com.example.firebase_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_auth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initData()

    }

    private fun initData(){
        auth = FirebaseAuth.getInstance()
//        auth.signOut()
        checkIfUserIsLoggedIn()

    }

    private fun checkIfUserIsLoggedIn(){
        val currentUser = auth.currentUser

        if (currentUser != null){
            startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}