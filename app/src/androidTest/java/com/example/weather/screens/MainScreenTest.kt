package com.example.weather.screens

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weather.MainActivity
import com.example.weather.WeatherApp
import com.example.weather.di.AppModule
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.main.WeatherMainScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {

    val testCity: String = "Oklahoma City"


    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val vm: MainViewModel = hiltViewModel()
            val navController = getNavController()
            WeatherMainScreen(navController, vm, testCity)
            //WeatherApp()
        }
    }

    @Test
    fun click_Search_Icon(){
        // click the icon button on the main screen
        composeTestRule.onNodeWithContentDescription("Search icon", substring = true, ignoreCase = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Search For Cities", substring = true, ignoreCase = true).assertIsDisplayed()
       // composeTestRule.onNodeWithContentDescription("Back Arrow", substring = true, ignoreCase = true).performClick()
    //    composeTestRule.onNodeWithText("Current Weather").assertIsDisplayed()

    }
}

@Composable
fun getNavController(): NavHostController {
    return rememberNavController()
}