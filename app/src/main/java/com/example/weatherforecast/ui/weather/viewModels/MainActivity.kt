package com.example.weatherforecast.ui.weather.viewModels

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.domain.weather.WeatherResponse
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        /*================Initializations=====================*/
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*================Observers=========================*/
        viewModel.weatherResponse.observe(this) { weatherResponse ->
            binding.apply {
                bind(this, weatherResponse)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun bind(activityMainBinding: ActivityMainBinding, weatherResponse: WeatherResponse) {
        activityMainBinding.tvCityName.text = getString(R.string.city)
        activityMainBinding.tvTemperature.text = weatherResponse.temperature
        activityMainBinding.tvDescription.text = weatherResponse.description
        activityMainBinding.tvWind.text = weatherResponse.wind

        val forecast1 = weatherResponse.forecast[0]
        val forecast2 = weatherResponse.forecast[1]
        val forecast3 = weatherResponse.forecast[2]
        val days = arrayOf(
            LocalDate.now().dayOfWeek.toString().lowercase()
                .replaceFirstChar { it.titlecase(Locale.getDefault()) },
            LocalDate.now().dayOfWeek.plus(1).toString().lowercase()
                .replaceFirstChar { it.titlecase(Locale.getDefault()) },
            LocalDate.now().dayOfWeek.plus(2).toString().lowercase()
                .replaceFirstChar { it.titlecase(Locale.getDefault()) },
        )
        activityMainBinding.tvForecast1Day.text = days[forecast1.day.minus(1)]
        activityMainBinding.tvForecast2Day.text = days[forecast2.day.minus(1)]
        activityMainBinding.tvForecast3Day.text = days[forecast3.day.minus(1)]
        activityMainBinding.tvForecast1.text = "${forecast1.temperature} / ${forecast1.wind}"
        activityMainBinding.tvForecast2.text = "${forecast2.temperature} / ${forecast2.wind}"
        activityMainBinding.tvForecast3.text = "${forecast3.temperature} / ${forecast3.wind}"
    }
}