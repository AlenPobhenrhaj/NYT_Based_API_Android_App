package com.example.nytbasedapiandroidapp.firebase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthHelper(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }

    fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                saveUserSession()
                callback(true)
            } else {
                // Handle registration failure, e.g., show an error message
                callback(false)
            }
        }
    }

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                saveUserSession()
                callback(true)
            } else {
                // Handle login failure, e.g., show an error message
                callback(false)
            }
        }
    }

    fun logout() {
        clearUserSession()
        auth.signOut()
    }

    private fun saveUserSession() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", true)
        editor.apply()
    }

    private fun clearUserSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
