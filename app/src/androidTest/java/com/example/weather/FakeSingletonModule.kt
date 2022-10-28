package com.example.weather

import com.example.weather.di.AppModule
import com.example.weather.network.WeatherApi
import com.example.weather.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class FakeSingletonModule {

//    @ExperimentalCoroutinesApi
//    @Provides
//    fun provideIoDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()

    @Provides
    fun provideFakeOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}