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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val textID = findViewById<EditText>(R.id.loginText)
        val textPassword = findViewById<EditText>(R.id.passwordText)
        val regButton = findViewById<Button>(R.id.RegButton)
        val signButton = findViewById<Button>(R.id.login_button)

        regButton.setOnClickListener {
            val regIntent = Intent(this.baseContext, RegistrationActivity::class.java)
            startActivity(regIntent)
        }

        signButton.setOnClickListener {
            val login = textID.text.toString().trim()
            val password = textPassword.text.toString().trim()
            if (login == "" || password == "") {
                Toast.makeText(this, R.string.toastLogInWrongInput, Toast.LENGTH_LONG).show()
            } else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, password)
                if (isAuth) {
                    Toast.makeText(this, R.string.toastLogIn, Toast.LENGTH_LONG).show()
                    textID.text.clear()
                    textPassword.text.clear()
                    val intent = Intent(this.baseContext, NotesActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, R.string.toastLogInWrong, Toast.LENGTH_LONG).show()
                }
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
            { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}