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

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPwd: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCancel: Button

    private lateinit var tvRegister: TextView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        etEmail = findViewById(R.id.editTextLoginEmail)
        etPwd = findViewById(R.id.editTextLoginPassword)
        tvRegister = findViewById(R.id.textViewRegister)

        btnLogin = findViewById(R.id.buttonLogin)
        btnCancel = findViewById(R.id.buttonLoginCancel)

        btnLogin.setOnClickListener({

            var email: String = etEmail.text.toString()
            var password: String = etPwd.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, {task ->

                    if (task.isSuccessful){

                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this,"Logged in Successfully", Toast.LENGTH_LONG).show()
                        finish()
                        etEmail.text = null
                        etPwd.text = null
                    }else{

                        Toast.makeText(this,task.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                })


        })

        btnCancel.setOnClickListener({

            etEmail.text = null
            etPwd.text = null
        })
        tvRegister.setOnClickListener({

            startActivity(Intent(this, RegisterActivity::class.java))

        })
    }
}