package com.example.banqu.weathernews.screen

import android.content.Context
import android.content.Intent
import com.example.banqu.weathernews.screen.location.LocationSelectActivity

/**
 * Created by banqu on 2018/03/21.
 */
object Navigator {

    fun showLocationSelectActivity(context: Context) {
        context.startActivity(Intent(context, LocationSelectActivity::class.java).apply {

        })
    }

}