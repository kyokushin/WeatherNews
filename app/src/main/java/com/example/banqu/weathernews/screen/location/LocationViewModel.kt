package com.example.banqu.weathernews.screen.location

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by banqu on 2018/03/21.
 */
class LocationViewModel : ViewModel() {
    var locationList = MutableLiveData<List<LocationListItem>>()
        private set
}