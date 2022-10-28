@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.weather.screens.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.WeatherApp
import com.example.weather.navigation.WeatherScreens
import com.example.weather.ui.theme.Purple200
import com.example.weather.widgets.WeatherAppBar

// how to search for other cities - search screen -> https://www.udemy.com/course/kotling-android-jetpack-compose-/learn/lecture/29768408#content


//Take text from search field and navigate to MainScreen with text passed as city query
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherSearchScreen(navController: NavController){
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Search For Cities",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
            ){
                navController.popBackStack()
            }
        }
    ) {

        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ){
                    Log.d("TAG", "SearchScreen: $it")
                    navController.navigate(WeatherScreens.MainScreen.name + "/$it")
                }

            }
        }

    }
}


@SuppressLint("SuspiciousIndentation")
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
){

    val searchQueryState = rememberSaveable { mutableStateOf(value = "") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty() //return true if not empty -- if empty -> not valid
    }

    Column(
        Modifier.background(MaterialTheme.colors.background)
    ) {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "City",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = ""
                    keyboardController!!.hide()
            }
        )

    }
}


//Search field
@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it},
        label = { Text(text = placeHolder)},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Cyan),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
        )
}
