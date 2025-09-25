package com.example.lab2.data.remote

import com.example.lab2.data.model.Company
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import kotlin.getValue


interface CompaniesApiService {
    @GET("companies")
    suspend fun getCompanies(): List<Company>
}


object CompaniesApi {
    private const val BASE_URL =
        "https://firestore.googleapis.com/v1/projects/appdev-lab2/databases/(default)/documents/"

    // add id token to header
    fun create(idToken: String): CompaniesApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $idToken") // 🔑 attach token
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CompaniesApiService::class.java)
    }
}
