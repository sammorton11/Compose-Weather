package com.example.weather.screens.main

import com.example.weather.model.Weather

data class WeatherState(
    val weatherInfo: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
