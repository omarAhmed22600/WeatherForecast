package com.example.weatherforecast.data.remote.weather.dataSource

import com.example.weatherforecast.data.api.APIConstants
import com.example.weatherforecast.domain.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiServices {
    @GET("${APIConstants.QUERY_GET_WEATHER_DATA}/cairo")
    suspend fun getWeather(): Response<WeatherResponse>

}