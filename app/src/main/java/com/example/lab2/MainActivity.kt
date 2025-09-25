package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab2.ui.theme.Lab2Theme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2Theme {
                Lab2App()
            }
        }
    }
}

@Composable
fun Lab2App() {
    CompaniesScreen()

}

@Preview
@Composable
fun Lab2AppPreview() {

}

@Composable
fun CompaniesScreen(viewModel: CompaniesViewModel = viewModel(), modifier: Modifier = Modifier) {
    val response = viewModel.responseState

    LaunchedEffect(Unit) {
        viewModel.getCompanies()
    }

    when (response) {
        is ResponseState.Success -> Text("Success")
        is ResponseState.Loading -> Text("Loading")
        is ResponseState.Error -> Text("Error")
    }

    Column {
        print(response)
    }


}
