package com.example.weather.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.navigation.WeatherScreens
import com.example.weather.screens.main.components.WeatherCard
import com.example.weather.widgets.WeatherAppBar


//Function to be called in WeatherNavigation file
@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
){

    val weatherData = weatherData(mainViewModel = mainViewModel, city = city)
    if (weatherData.loading == true){
        CircularProgressIndicator()

        //Once there is data present, populate the UI
    } else if (weatherData.data != null){
        Column(
        ) {
            MainScaffold(weatherData.data!!, navController)
        }
    }
}

//Gets weather data from view model
@Composable
fun weatherData(
    mainViewModel: MainViewModel, city: String?
): DataOrException<Weather, Boolean, Exception> {

    return produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "$city")
    }.value
}


//Navigation bar with search icon to open the Search Screen
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ", ${weather.city.country}",
            onAddActionClicked = { navController.navigate(WeatherScreens.SearchScreen.name) },
            elevation = 10.dp){
            Log.d("TAG", "MainScaffold: button clicked")
        }
    }) {
        //MainContent(weatherData = weather)
        WeatherCard(weather)
    }
}















