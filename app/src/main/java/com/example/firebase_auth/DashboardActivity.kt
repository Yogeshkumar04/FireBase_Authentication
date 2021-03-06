package com.example.firebase_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_auth.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initData()

    }

    private fun initData(){
        auth = FirebaseAuth.getInstance()
        setUserEmail()
        clickListener()
    }

    private fun clickListener(){
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    private fun getCurrentUserEmail():String?{
        return auth.currentUser?.email

    }

    private fun setUserEmail(){
        binding.txtUserEmail.text = "Welcome " + getCurrentUserEmail()
    }
}