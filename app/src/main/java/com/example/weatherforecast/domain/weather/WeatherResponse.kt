package com.example.weatherforecast.domain.weather

data class WeatherResponse(
    val description: String,
    val forecast: List<Forecast>,
    val temperature: String,
    val wind: String
)