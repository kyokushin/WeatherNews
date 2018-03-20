package com.example.banqu.weathernews.common

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by banqu on 2018/03/19.
 */
object Schedulers {

    fun main() = AndroidSchedulers.mainThread()

    fun io() = Schedulers.io()
}