package com.example.nytbasedapiandroidapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.nytbasedapiandroidapp.R
import com.example.nytbasedapiandroidapp.databinding.ActivityMainBinding
import com.example.nytbasedapiandroidapp.firebase.FirebaseAuthHelper
import com.example.nytbasedapiandroidapp.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authHelper = FirebaseAuthHelper(this)

        if (!authHelper.isLoggedIn()) {
            redirectToLoginActivity()
        } else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, MainFragment::class.java, null)
            }
        }
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
