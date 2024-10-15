package com.example.androidhomework.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomework.loginAndRegistration.LoginActivity
import com.example.androidhomework.R

class SecondOnboardingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_onboarding_screen)

        val button = findViewById<Button>(R.id.buttonNext)
        button.setOnClickListener {
            val intent = Intent(this.baseContext, ThirdOnboardingScreen::class.java)
            startActivity(intent)
        }
        val skipButton = findViewById<Button>(R.id.skip_button)
        skipButton.setOnClickListener {
            val skipIntent = Intent(this.baseContext, LoginActivity::class.java)
            startActivity(skipIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}