package com.dhrutikambar.internetconnectivitymanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dhrutikambar.internetconnectivitymanager.ui.theme.InternetConnectivityManagerTheme
import com.dhrutikambar.internetconnectivitymanager.viewmodel.NetworkViewModel
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InternetConnectivityManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel = NetworkViewModel()
    val state by viewModel.isConnected.observeAsState(true)
    if (state) {
        Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(context, "Not Connected", Toast.LENGTH_LONG).show()

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InternetConnectivityManagerTheme {
        Greeting("Android")
    }
}