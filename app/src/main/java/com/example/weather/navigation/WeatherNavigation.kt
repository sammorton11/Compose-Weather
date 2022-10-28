package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.screens.about.WeatherAboutScreen
import com.example.weather.screens.favorites.WeatherFavoriteScreen
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.main.WeatherMainScreen
import com.example.weather.screens.search.WeatherSearchScreen
import com.example.weather.screens.settings.WeatherSettingsScreen
import com.example.weather.screens.splash.WeatherSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ){

        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        // changing the type to string because this expects a list
        val route = WeatherScreens.MainScreen.name

        composable("$route/{city}",
            listOf(navArgument("city"){ type = NavType.StringType })){ navBack ->

            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.AboutScreen.name){
            WeatherAboutScreen(navController = navController)
        }

        composable(WeatherScreens.SettingsScreen.name){
            WeatherSettingsScreen(navController = navController)
        }

        composable(WeatherScreens.FavoriteScreen.name){
            WeatherFavoriteScreen(navController = navController)
        }

        composable(WeatherScreens.SearchScreen.name){
            WeatherSearchScreen(navController = navController)
        }
    }
}

    

