package com.example.banqu.weathernews.screen.weather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.banqu.weathernews.R
import com.example.banqu.weathernews.api.WeatherHackRequest
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
            (view?.text as TextView).text = response.title
        })

        WeatherHackRequest.getWeatherAt(400040)
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
