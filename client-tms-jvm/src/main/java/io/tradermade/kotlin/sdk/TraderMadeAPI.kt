package io.tradermade.kotlin.sdk


import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class TraderMadeAPI(private val apiKey: String) {

    private val baseUrl = "https://marketdata.tradermade.com/api/v1"

    private fun sendRequest(endpoint: String, params: Map<String, String>): String {
        val urlBuilder = StringBuilder("$baseUrl$endpoint?api_key=$apiKey")
        for ((key, value) in params) {
            urlBuilder.append("&$key=$value")
        }

        val url = URL(urlBuilder.toString())
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"

        BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
            var data = reader.readText()
            println(data)
            return data
        }
    }

    fun getLiveData(currencyPairs: String): String {
        var data = sendRequest("/live", mapOf("currency" to currencyPairs))
        println(data)
        return data
    }

    fun getHistoricalData(currencyPairs: String, date: String): String {
        return sendRequest("/historical", mapOf("currency" to currencyPairs, "date" to date))
    }

    fun getTimeSeriesData(currencyPair: String, startDate: String, endDate: String, interval: String, period: String): String {
        return sendRequest(
            "/timeseries", mapOf(
                "currency" to currencyPair,
                "start_date" to startDate,
                "end_date" to endDate,
                "interval" to interval,
                "period" to period
            )
        )
    }

    fun convertCurrency(fromCurrency: String, toCurrency: String, amount: Double): String {
        return sendRequest(
            "/convert", mapOf(
                "from" to fromCurrency,
                "to" to toCurrency,
                "amount" to amount.toString()
            )
        )
    }
}

fun main() {
    try {
        val api = TraderMadeAPI("YOUR_API_KEY")

        // Example usage
        val liveData = api.getLiveData("EURUSD,GBPUSD")
        println("Live Data: $liveData")

        val historicalData = api.getHistoricalData("EURUSD", "2023-08-01")
        println("Historical Data: $historicalData")

        val timeSeriesData = api.getTimeSeriesData("EURUSD", "2023-08-01", "2023-08-10", "daily", "1")
        println("Time Series Data: $timeSeriesData")

        val convertedAmount = api.convertCurrency("EUR", "USD", 1000.0)
        println("Converted Amount: $convertedAmount")

    } catch (e: Exception) {
        e.printStackTrace()
    }
}



