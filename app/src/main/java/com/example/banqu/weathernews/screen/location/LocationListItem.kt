package com.example.banqu.weathernews.screen.location

import com.example.banqu.weathernews.common.City
import com.example.banqu.weathernews.common.Prefecture

/**
 * Created by banqu on 2018/03/21.
 */
interface LocationListItem {
    val type: Type

    class PrefectureItem(val prefecture: Prefecture) : LocationListItem {
        override val type = Type.Prefecture
        var isOpen = false
    }

    class CityItem(val city: City) : LocationListItem {
        override val type = Type.City
    }

    enum class Type {
        Prefecture,
        City
    }
}
