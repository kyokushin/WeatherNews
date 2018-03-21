package com.example.banqu.weathernews.screen.location

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.banqu.weathernews.R
import com.example.banqu.weathernews.common.LocationResponse
import kotlinx.android.synthetic.main.activity_location_select.*
import org.simpleframework.xml.core.Persister

class LocationSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_select)
        val locationResponse = getLocations()
        setSupportActionBar(toolbar)


    }

    fun getLocations() = Persister().read(LocationResponse::class.java,
            resources.openRawResource(R.raw.primary_area))

}
