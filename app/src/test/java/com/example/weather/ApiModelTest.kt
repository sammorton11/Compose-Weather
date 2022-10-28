package com.example.weather

import com.example.weather.model.Weather
import com.example.weather.util.Constants.fakeResponse
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class ApiModelTest {

    @Test
    fun canParseResponse() {
        val model = Gson().fromJson(fakeResponse, Weather::class.java)
        Assert.assertTrue(model != null)
    }
}