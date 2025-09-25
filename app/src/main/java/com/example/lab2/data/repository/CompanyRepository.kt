package com.example.lab2.data.repository

import android.util.Log
import com.example.lab2.data.model.Company
import com.example.lab2.data.remote.CompaniesApi
import com.example.lab2.data.remote.CompaniesApiService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import retrofit2.await

class CompanyRepository {

    private suspend fun getApi(): CompaniesApiService {
        // Get Firebase ID token
        val user = FirebaseAuth.getInstance().currentUser
            ?: throw Exception("No logged in user")

        val token = user.getIdToken(true).await().token
            ?: throw Exception("Token is null")

        // Build API with token
        return CompaniesApi.create(token)
    }

    suspend fun loadCompanies(): List<Company> {
        val api = getApi()
        val response = api.getCompanies()

        Log.d("Firestore", "Companies JSON: $response")

        return response //TODO: convert response to list maybe?
    }
}
