package com.example.lab2.data.remote

import com.example.lab2.data.model.Company
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import kotlin.getValue


interface CompaniesApiService {
    @GET("companies")
    suspend fun getCompanies(): List<Company>
}


object CompaniesApi {
    private const val BASE_URL = "somethingj"

    val api: CompaniesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CompaniesApiService::class.java)
    }
}
