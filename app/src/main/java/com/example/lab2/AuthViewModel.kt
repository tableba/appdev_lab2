package com.example.lab2

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    init {
        autoSignIn()
    }

    private fun autoSignIn() {
        // Check if user is already signed in
        if (auth.currentUser == null) {
            auth.signInAnonymously()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth", "Signed in anonymously")
                    } else {
                        Log.e("Auth", "Anonymous sign-in failed", task.exception)
                    }
                }
        } else {
            Log.d("Auth", "User already signed in: ${auth.currentUser?.uid}")
        }
    }

    fun getCurrentUser() = auth.currentUser
}
