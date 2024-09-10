# TraderMade JVM Client written in Kotlin - RESTful APIs 

## Welcome to the official JVM client library SDK for the TraderMade REST and WebSocket API, crafted in Kotlin. This SDK is compatible with any JVM language, including Android SDK version 21 and above. To begin, please refer to the Getting Started section in our documentation and explore the sample directory for code examples.


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

To request real-time and historical market rates from marketdata.tradermade.com, you will need to sign up for a free accout at https://tradermade.com/signup and then navigate to the https://tradermade.com/dashboard page to obtain your API key. Learn more about our Forex api at https://tradermade.com/forex.

Before running the sample app you will need to insert your api key into the MainActivity.kt file.

    val api = TraderMadeAPI("YOUR_API_KEY")

Once you have insert your key you can start using the API, this includes the following functionality:


# TraderMade REST API Endpoints Overview

## 1. Live Forex Data Endpoint
This endpoint provides real-time quotes for various currency pairs, cryptos, indices, commodities, and stock CFDs.

- **URL Format**:  
  `/live?currency=EURUSD,GBPUSD&api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **currency**: Comma-separated list of currency pairs or instruments (e.g., EURUSD, GBPUSD).
  - **api_key**: Your API key for authentication.

- **Response**:  
  Provides live bid, ask, and mid prices for each currency pair requested.

## 2. Historical Forex Data Endpoint
This endpoint provides daily historical data (OHLC) for currency pairs.

- **URL Format**:  
  `/historical?date=YYYY-MM-DD&currency=EURUSD,GBPUSD&api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **date**: The specific date for which data is requested (YYYY-MM-DD).
  - **currency**: Comma-separated list of currency pairs or instruments.
  - **api_key**: Your API key.

- **Response**:  
  Daily open, high, low, and close prices for the requested date.

- **Note**:  
  For more than one day of data, the **timeseries** endpoint should be used instead.

## 3. Timeseries Forex Data Endpoint
This endpoint provides historical OHLC data over a range of dates for currency pairs, available at daily, hourly, or minute intervals.

- **URL Format**:  
  `/timeseries?currency=EURUSD&start_date=YYYY-MM-DD&end_date=YYYY-MM-DD&interval=daily&api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **currency**: Currency pair (e.g., EURUSD).
  - **start_date**: The starting date for data retrieval (YYYY-MM-DD).
  - **end_date**: The end date for data retrieval (YYYY-MM-DD).
  - **interval**: Data granularity (daily, hourly, or minute).
  - **api_key**: Your API key.

- **Optional Parameters**:  
  - **format**: Format of the data (e.g., records, csv, index, columns).
  - **period**: For hourly and minute intervals, define how often data points are retrieved (e.g., 1, 5, 10 minutes).

- **Response**:  
  OHLC data for the requested date range and interval.

- **Note**:  
  - **Daily data**: Up to 15 years of data.  
  - **Hourly data**: Up to 12 months (1 month per request).  
  - **Minute data**: Up to 1 month (2-day maximum per request).

## 4. Tick Historical Data Endpoint
This endpoint provides tick-by-tick historical data for currency pairs. Available only to users with the Advanced Plan.

- **URL Format**:  
  `/tick_historical/{symbol}/{startdate}/{enddate}?api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **symbol**: The currency pair (e.g., EURUSD).
  - **startdate**: Start date and time (YYYY-MM-DD HH:MM).
  - **enddate**: End date and time (YYYY-MM-DD HH:MM).
  - **api_key**: Your API key.

- **Response**:  
  Tick data within the specified date and time range.

- **Note**:  
  Tick data can be requested in chunks of up to 60 minutes, with a maximum of one month of history.

## 5. Tick Historical Sample Endpoint
This provides a sample of tick data going back four working days (excluding today). Available for all plans.

- **URL Format**:  
  `/tick_historical_sample/{symbol}/{startdate}/{enddate}?api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **symbol**: The currency pair (e.g., EURUSD).
  - **startdate**: Start date and time (YYYY-MM-DD HH:MM).
  - **enddate**: End date and time (YYYY-MM-DD HH:MM).
  - **api_key**: Your API key.

- **Response**:  
  Provides tick data sample for a limited date range (30 minutes).

## 6. Currency Conversion Endpoint
This endpoint performs real-time currency conversion between two currencies.

- **URL Format**:  
  `/convert?from=EUR&to=USD&amount=1000&api_key=YOUR_API_KEY`
  
- **Required Parameters**:  
  - **from**: The base currency (e.g., EUR).
  - **to**: The quote currency (e.g., USD).
  - **amount**: The amount to convert (e.g., 1000).
  - **api_key**: Your API key.

- **Response**:  
  The converted amount and conversion rate between the two currencies.

---

## Common Errors and Their Meanings:
- **401**: API key is invalid or the plan doesn't allow access.
- **204**: No data for the requested date or currency pair.
- **400**: Validation failed, likely due to an incorrect parameter (e.g., wrong date format).
- **403**: No data for the weekend or the request is beyond the allowed historical limits.
- **503**: Rate limiting has been exceeded.











