package com.takingnotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val registerEmail = findViewById<EditText>(R.id.registerSetEmail)
        val registerPassword = findViewById<EditText>(R.id.registerSetPassword)
        val registerConfirmPassword= findViewById<EditText>(R.id.registerRePassword)
        val registerButton = findViewById<Button>(R.id.registerConfirmButton)
        var auth: FirebaseAuth = Firebase.auth

        registerButton.setOnClickListener{
            var email = registerEmail.text.toString()
            var password = registerPassword.text.toString()
            var rePassword = registerConfirmPassword.text.toString()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            } else if (!TextUtils.isEmpty(email)) {
                if(password.equals(rePassword)){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Successfully created an account!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}