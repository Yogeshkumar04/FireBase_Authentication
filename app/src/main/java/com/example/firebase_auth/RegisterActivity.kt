package com.example.firebase_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase_auth.databinding.ActivityLoginBinding
import com.example.firebase_auth.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initData()
    }

    private fun initData(){
        auth = FirebaseAuth.getInstance()
        clickListener()
    }

    private fun clickListener(){
        binding.btnRegister.setOnClickListener {
            createUser()
        }

        binding.llHaveAccount.setOnClickListener {

        }
    }

    private fun createUser(){

        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var cPassword = binding.etcPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && cPassword.isNotEmpty()){
            //save user detail
            if (password == cPassword){
                saveUser(email,password)
            }else{
                Toast.makeText(this,"Password mismatch",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"All inputs are required", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUser(email: String, password: String){

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{

                checkResults(it.isSuccessful)

            }
    }

    private fun checkResults(isSuccess: Boolean){
        if (isSuccess){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Unable to Create your account",Toast.LENGTH_LONG).show()
        }
    }


}