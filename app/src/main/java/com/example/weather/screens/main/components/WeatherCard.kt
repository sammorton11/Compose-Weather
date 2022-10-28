package com.example.weather.screens.main.components

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.model.Weather
import com.example.weather.screens.main.DataDisplay
import com.example.weather.screens.main.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import com.example.weather.R
import com.example.weather.model.WeatherObject
import com.example.weather.ui.theme.LightOrange

@Composable
fun WeatherCard(weatherData: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                // REFRESH DATA
            },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            val currentWeather = weatherData.list[0]
            //Weather Details
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)){
                items(weatherData.list[0].weather.size){ index ->

                    val currentWeatherObjectList = weatherData.list[0].weather[index] // weather - WeatherObject data class

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 25.dp)
                    ) {
                        Text("${weatherData.city.name}, ${weatherData.city.country}",
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(end = 15.dp),
                            fontSize = 27.sp
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = iconMapper(currentWeatherObjectList)),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    
                    
                    Text(
                        text = "${currentWeather.temp.day.toInt()}°F",
                        modifier = Modifier.padding(bottom = 20.dp),
                        fontSize = 25.sp
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "${currentWeatherObjectList.main}")
                        Text("${currentWeather.temp.night.toInt()}°F  (night)")
                        Text("${currentWeather.temp.morn.toInt()}°F  (morning)")
                        Text("${currentWeather.temp.eve.toInt()}°F  (evening)")
                        Text("${currentWeather.temp.max.toInt()}°F  maximum")
                        Text("${currentWeather.temp.min.toInt()}°F  minimum")
                        Spacer(modifier = Modifier.padding(bottom = 15.dp))
                        Text("Feels like:   ${currentWeather.feels_like.day.toInt()}°F  (day)")
                        Text("Feels like:   ${currentWeather.feels_like.night.toInt()}°F  (night)")
                        Text(text = "Population: ${weatherData.city.population}")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        DataDisplay(
                            value = currentWeather.pressure,
                            unit = " hpa",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                            iconTint = Color.Black,
                            textStyle = TextStyle(color = Color.Black)
                        )
                        DataDisplay(
                            value = currentWeather.humidity,
                            unit = "%",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                            iconTint = Color.Black,
                            textStyle = TextStyle(color = Color.Black)
                        )
                        DataDisplay(
                            value = currentWeather.gust.roundToInt(),
                            unit = " km/h",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                            iconTint = Color.Black,
                            textStyle = TextStyle(color = Color.Black)
                        )
                    }
                }


            }
        }
    }
}


fun iconMapper(
    currentWeatherObjectList: WeatherObject
): Int {
   return when (currentWeatherObjectList.icon) {
       "04d" -> R.drawable.cloudy
       else -> R.drawable.cloudy
   }
}