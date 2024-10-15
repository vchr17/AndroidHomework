package com.example.androidhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        val userLogin: EditText = findViewById(R.id.RegLogin)
        val userEmail: EditText = findViewById(R.id.RegEmail)
        val userPassword: EditText = findViewById(R.id.RegPass)
        val regButton: Button = findViewById(R.id.RegButton)
        val backToLogin: Button = findViewById(R.id.BackToLogin)

        regButton.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()
            if (login == "" || email == "" || password == "")
                Toast.makeText(this, R.string.reg_failed, Toast.LENGTH_LONG).show()
            else {
            val user = User(login, email, password)
            val db = DbHelper(this, null)
            db.addUser(user)
            Toast.makeText(this, R.string.reg_succ, Toast.LENGTH_LONG).show()
            userLogin.text.clear()
            userEmail.text.clear()
            userPassword.text.clear()
            val intent = Intent(this.baseContext, LoginActivity::class.java)
            startActivity(intent)
            }


        }
        backToLogin.setOnClickListener {
            backToLogin.setOnClickListener {
                val backIntent = Intent(this.baseContext, LoginActivity::class.java)
                startActivity(backIntent)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}