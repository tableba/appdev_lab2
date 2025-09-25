package com.example.lab2

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.unit.dp
import com.example.lab2.ui.theme.Lab2Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // temp, to see if login works (spoiler: it does not.)
        FirebaseAuth.getInstance().currentUser ?: run {
            FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth", "Signed in anonymously")
                    } else {
                        Log.e("Auth", "Anonymous sign-in failed", task.exception)
                    }
                }
        }

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
fun CompaniesScreen(
    viewModel: CompaniesViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val response = viewModel.responseState

    LaunchedEffect(Unit) {
        viewModel.getCompanies()
    }

    when (response) {
       // test if there is any response / what the response is
        is ResponseState.Success ->
            Column (modifier = Modifier.fillMaxSize().padding(15.dp)) {
                response.companies.forEach { company ->
                    Text(text = company.city)
                }
            }
        is ResponseState.Loading -> Text("Loading")
        is ResponseState.Error -> response.msg?.let { Text(text = it) }
    }


}

//TODO: get anonnymous login to work ; try setting up a login page if it does not
// after login works try getting companies json
// if that is done then gg