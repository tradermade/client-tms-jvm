# TraderMade JVM Client written in Kotlin - RESTful APIs 

Welcome to the official JVM client library SDK for the [TraderMade](https://tradermade.com) REST API, crafted in Kotlin. This SDK is compatible with any JVM language, including Android SDK version 21 and higher. Please refer to the [Getting Started](https://tradermade.com/docs/restful-api) section in our documentation and explore the [sample](https://github.com/tradermade/client-tms-jvm/blob/main/sample/TestClientJVM/app/src/main/java/io/tradermade/test_client_jvm/MainActivity.kt) directory for code examples.


## Install

To use the SDK in a Gradle project:


Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.tradermade:client-tms-jvm:Tag'
	}


Please see the GitHub release for the current version to repalce the tag place holder.

## Getting Started

To request real-time and historical market rates from marketdata.tradermade.com, you will need to sign up for a free account at https://tradermade.com/signup and then navigate to the https://tradermade.com/dashboard page to obtain your API key. Learn more about our Forex api at https://tradermade.com/forex.

    val api = TraderMadeAPI("YOUR_API_KEY")

Once you have inserted your key you can start using the API, this includes the following functionality:

# TraderMade REST API Endpoints Overview

## 1. Live Forex Data Endpoint
This endpoint provides real-time quotes for various currency pairs, cryptos, indices, commodities, and stock CFDs.

    val liveData = api.getLiveData("EURUSD,GBPUSD")

## 2. Historical Forex Data Endpoint
This endpoint provides daily historical data (OHLC) for currency pairs.

	val historicalData = api.getHistoricalData("EURUSD", "2023-08-01")
 
- **Note**:  
  For more than one day of data for charts or data tables, the **timeseries** endpoint should be used instead.

## 3. Timeseries Forex Data Endpoint
This endpoint provides historical OHLC data over a range of dates for currency pairs, available at daily, hourly, or minute intervals.

	val timeSeriesData = api.getTimeSeriesData("EURUSD", "2023-08-01", "2023-08-10", "daily", "1")

- **Note**:  
  - **Daily data**: Up to 15 years of data (1 year max per request).  
  - **Hourly data**: Up to 12 months (1 month per request).  
  - **Minute data**: Up to 1 month (2-day maximum per request).


## 4. Currency Conversion Endpoint
This endpoint performs real-time currency conversion between two currencies.

 	val convertedAmount = api.convertCurrency("EUR", "USD", 1000.0)

---

Please see the more detailed [sample](https://github.com/tradermade/client-tms-jvm/blob/main/sample/TestClientJVM/app/src/main/java/io/tradermade/test_client_jvm/MainActivity.kt) code.  

## Common Errors and Their Meanings:
- **401**: API key is invalid or the plan doesn't allow access.
- **204**: No data for the requested date or currency pair.
- **400**: Validation failed, likely due to an incorrect parameter (e.g., wrong date format).
- **403**: No data for the weekend or the request is beyond the allowed historical limits.
- **503**: Rate limiting has been exceeded.











