package com.example.banqu.weathernews.screen.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.banqu.weathernews.api.WeatherResponse
import com.example.banqu.weathernews.common.ImageLoader

/**
 * Created by banqu on 2018/03/19.
 */
class WeatherViewModel : ViewModel() {

    private val weatherLiveData = MutableLiveData<WeatherResponse>()
    val imageLoader = ImageLoader()

    fun getWeatherResponse() = weatherLiveData

}