package com.example.lab2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2.data.model.Company
import com.example.lab2.data.repository.CompanyRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ResponseState {
    data class Success(val companies: List<Company>): ResponseState
    object Loading: ResponseState
    data class Error(val msg: String?): ResponseState



}

class CompaniesViewModel: ViewModel() {
    private val repo = CompanyRepository()

    var responseState: ResponseState by mutableStateOf(ResponseState.Loading)


    fun getCompanies() {
        viewModelScope.launch {
            responseState = try {
                val response = repo.loadCompanies()
                ResponseState.Success(response)
            } catch (e: Exception) {
                ResponseState.Error(e.message)
            }
        }
    }

}