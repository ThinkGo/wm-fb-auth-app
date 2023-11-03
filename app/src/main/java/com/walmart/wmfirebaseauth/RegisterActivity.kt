package com.walmart.wmfirebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPwd: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnCancel: Button

    private lateinit var tvLogin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        etEmail = findViewById(R.id.editTextRegisterEmail)
        etPwd = findViewById(R.id.editTextRegisterPassword)

        btnRegister = findViewById(R.id.buttonRegister)
        btnCancel = findViewById(R.id.buttonRegisterCancel)

        tvLogin = findViewById(R.id.textViewLogin)

        btnRegister.setOnClickListener {

            var email: String = etEmail.text.toString()
            var pwd: String = etPwd.text.toString()

            auth.createUserWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, { task ->

                    if (task.isSuccessful){

                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this,"User Created SuccessFuly",
                            Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this,task.exception.toString(),
                            Toast.LENGTH_LONG).show()
                    }
                })
            etEmail.text = null
            etPwd.text = null

        }

        btnCancel.setOnClickListener {

            etEmail.text = null
            etPwd.text = null

        }

        tvLogin.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}