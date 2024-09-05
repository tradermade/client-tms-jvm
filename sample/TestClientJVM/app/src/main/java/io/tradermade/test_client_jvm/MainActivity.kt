package io.tradermade.test_client_jvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.tradermade.test_client_jvm.ui.theme.TestClientJVMTheme
import io.tradermade.kotlin.sdk.TraderMadeAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Replace "YOUR_API_KEY" with your actual API key
        val api = TraderMadeAPI("CxbJIkb_B2jpfUg3jnJp")

        // Launch a coroutine to perform the network operations
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val liveData = api.getLiveData("EURUSD,GBPUSD")
                print(liveData)
                val historicalData = api.getHistoricalData("EURUSD", "2023-08-01")
                val timeSeriesData = api.getTimeSeriesData("EURUSD", "2023-08-01", "2023-08-10", "daily", "1")
                val convertedAmount = api.convertCurrency("EUR", "USD", 1000.0)

                // Update the UI on the main thread
                withContext(Dispatchers.Main) {
                    setContent {
                        TestClientJVMTheme {
                            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                Greeting(
                                    name = """
                                        Live Data: $liveData
                                        Historical Data: $historicalData
                                        Time Series Data: $timeSeriesData
                                        Converted Amount: $convertedAmount
                                    """.trimIndent(),
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    setContent {
                        TestClientJVMTheme {
                            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                Greeting(
                                    name = "Failed to fetch data: ${e.message}",
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestClientJVMTheme {
        Greeting("Sample Data")
    }
}
