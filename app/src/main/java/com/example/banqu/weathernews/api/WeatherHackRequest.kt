package com.example.banqu.weathernews.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by banqu on 2018/03/19.
 * LiveDoorのWeather Hackリクエスト
 */
object WeatherHackRequest {

    private const val URL = "http://weather.livedoor.com/forecast/webservice/"
    private val retrofit: Retrofit
    private val service: Service

    init {


        retrofit = Retrofit.Builder()
//                .client(
//                        OkHttpClient.Builder()
//                                .addInterceptor(
//                                        HttpLoggingInterceptor().apply {
//                                            level = HttpLoggingInterceptor.Level.BODY
//                                        })
//                                .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .build()
        service = retrofit.create(Service::class.java)
    }

    fun getWeatherAt(code: Int) = service.getLocationWeather(code)

    interface Service {

        @GET("json/v1")
        fun getLocationWeather(@Query("city") cityCode: Int): Single<Response<WeatherResponse>>

    }

}