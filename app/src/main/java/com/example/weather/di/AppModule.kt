package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.data.WeatherDao

import com.example.weather.network.WeatherApi
import com.example.weather.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// see video 177 - https://www.udemy.com/course/kotling-android-jetpack-compose-/learn/lecture/29767742#content

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Singleton
//    @Provides
//    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao
//            = weatherDatabase.weatherDao()
//
//    @Singleton
//    @Provides
//    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase
//            = Room.databaseBuilder(
//        context,
//        WeatherDatabase::class.java,
//        "weather_database")
//        .fallbackToDestructiveMigration()
//        .build()

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}