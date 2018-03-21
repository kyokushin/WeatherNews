package com.example.banqu.weathernews.screen.location

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.banqu.weathernews.R
import com.example.banqu.weathernews.common.LocationResponse
import com.example.banqu.weathernews.common.Schedulers
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_location_select.*
import kotlinx.android.synthetic.main.content_location_select.*
import org.simpleframework.xml.core.Persister

class LocationSelectActivity : AppCompatActivity() {

    lateinit var viewModel: LocationViewModel
    lateinit var adapter: LocationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_select)
        setSupportActionBar(toolbar)

        adapter = LocationListAdapter()

        recyclerview_locations.adapter = adapter
        recyclerview_locations.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        viewModel.locationList.observe(this, Observer { locationList ->
            adapter.locationList.addAll(locationList!!)
            adapter.notifyDataSetChanged()
        })

        Single.create<List<LocationListItem>> { emitter ->
            emitter.onSuccess(getLocations().channel.source
                    .map { LocationListItem.PrefectureItem(it) }
                    .toList())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.main())
                .subscribeBy(
                        onSuccess = {
                            viewModel.locationList.postValue(it)
                        },
                        onError = {}
                )

    }

    fun getLocations() = Persister().read(LocationResponse::class.java,
            resources.openRawResource(R.raw.primary_area))

}
