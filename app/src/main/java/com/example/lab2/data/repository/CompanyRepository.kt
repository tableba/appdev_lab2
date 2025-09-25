package com.example.lab2.data.repository

import com.example.lab2.data.remote.CompaniesApi
import com.example.lab2.data.model.Company

// kind of an interface between remote and model
class CompanyRepository {
    private val api = CompaniesApi.api

    suspend fun loadCompanies(): List<Company> {
        return api.getCompanies()
    }
}