package com.example.weatherforecast.data.remote.weather.repository

import com.example.weatherforecast.data.remote.weather.dataSource.WeatherApiServices
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(private val weatherApiServices: WeatherApiServices) {
    suspend fun getWeather() = weatherApiServices.getWeather()
}