package com.example.weather.repository

import android.util.Log
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.network.WeatherApi
import retrofit2.Response
import javax.inject.Inject

// get data from api
class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String)
    : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception){
            Log.d("ERROR", "getWeather: $e")
            return DataOrException(e = e)
        }
        //Log.d("INSIDE", "getWeather: ${response.city.name}")
        return DataOrException(data = response)
    }
}