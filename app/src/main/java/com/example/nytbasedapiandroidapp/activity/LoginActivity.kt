package com.example.nytbasedapiandroidapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nytbasedapiandroidapp.databinding.ActivityLoginBinding
import com.example.nytbasedapiandroidapp.databinding.ActivityMainBinding
import com.example.nytbasedapiandroidapp.firebase.FirebaseAuthHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authHelper = FirebaseAuthHelper(this)

        binding.loginButton.setOnClickListener {
            loginUser()
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        if (email.isEmpty()) {
            binding.emailInput.error = "Email is required"
            binding.emailInput.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.passwordInput.error = "Password is required"
            binding.passwordInput.requestFocus()
            return
        }

        authHelper.login(email, password) { success ->
            if (success) {
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
