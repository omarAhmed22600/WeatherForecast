package com.example.weatherforecast.ui.weather.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.remote.weather.repository.WeatherRepository
import com.example.weatherforecast.domain.weather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse>
        get() = _weatherResponse

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        weatherRepository.getWeather().let { response ->
            if (response.isSuccessful) {
                _weatherResponse.postValue(response.body())
            } else {
                Log.d(TAG, "Error getting weather information:\n${response.message()}")
            }

        }
    }

    companion object {
        private const val TAG: String = "WeatherViewModel"
    }

}