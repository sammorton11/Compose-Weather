package com.example.weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Search bar ui with icon buttons to search and open the 'more options' list
@Composable
fun WeatherAppBar(
    title: String = "",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 5.dp,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {})
{

    TopAppBar(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        title = {
                Text(text = title, fontSize = 17.sp, textAlign = TextAlign.Start)
            },
        //Search icon -- More icon
        actions = {
            //if on the main screen -- populate the icon buttons
            if (isMainScreen){
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search icon" )
                }

                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More icon" )
                }
            } else Box {}
        },

        //Back icon arrow
        navigationIcon = {
             if (icon != null){
                 Icon(
                     imageVector = icon,
                     contentDescription = "Back Arrow",
                     tint = MaterialTheme.colors.onSecondary,
                     modifier = Modifier.clickable {
                         onButtonClicked.invoke()
                     }.scale(0.9f)
                 )
             }

        },
        backgroundColor = Color.White,
        elevation = elevation
    )
}