package com.example.banqu.weathernews.screen.weather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banqu.weathernews.R
import com.example.banqu.weathernews.api.WeatherRequest
import com.example.banqu.weathernews.api.WeatherResponse
import com.example.banqu.weathernews.common.Schedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherViewModel.getWeatherResponse().observe(this, Observer<WeatherResponse> { it ->
            val response = it ?: return@Observer
            view?.title?.text = response.location.city
            val temperature = response.forecasts[0].temperature
            view?.temperature?.text = when {
                temperature.max != null && temperature.min != null ->
                    "${temperature.max?.celsius}℃ / ${temperature.min?.celsius} ℃"
                temperature.min != null ->
                    "- / ${temperature.min?.celsius} ℃"
                temperature.max != null ->
                    "${temperature.max?.celsius}℃ / -"
                else -> ""
            }

            val iconWeather = view?.icon_weather
            if (iconWeather != null) {
                weatherViewModel.imageLoader.loadImage(iconWeather, response.forecasts[0].image.url)
            }
        })

        WeatherRequest.getWeatherAt(400040)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.main())
                .subscribeBy(onSuccess = { response ->
                    weatherViewModel.getWeatherResponse().postValue(response.body())
                },
                        onError = {
                            val view = view ?: return@subscribeBy
                            Snackbar.make(view, "リクエストに失敗しました", Snackbar.LENGTH_LONG).show()
                        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

}
