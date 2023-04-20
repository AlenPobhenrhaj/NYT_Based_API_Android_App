package com.example.nytbasedapiandroidapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nytbasedapiandroidapp.databinding.ActivityRegisterBinding
import com.example.nytbasedapiandroidapp.firebase.FirebaseAuthHelper
import android.content.SharedPreferences

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize authHelper and sharedPreferences here
        authHelper = FirebaseAuthHelper(this)
        sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE)

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                authHelper.register(email, password) { isSuccessful ->
                    if (isSuccessful) {
                        sharedPreferences.edit().putBoolean("is_logged_in", true).apply()
                        redirectToMainActivity()
                    } else {
                        binding.registerErrorMessage.text = "Registration failed. Please try again."
                    }
                }
            } else {
                binding.registerErrorMessage.text = "Passwords do not match."
            }
        }
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
