package com.example.androidhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstOnboardingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_onboarding_screen)
        val intent = Intent(this.baseContext, SecondOnboardingScreen::class.java)
        val skipIntent = Intent(this.baseContext, LoginActivity::class.java)
        val button = findViewById<Button>(R.id.buttonNext)
        button.setOnClickListener {
            startActivity(intent)
        }
        val skipButton = findViewById<Button>(R.id.skip_button)
        skipButton.setOnClickListener {
            startActivity(skipIntent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}