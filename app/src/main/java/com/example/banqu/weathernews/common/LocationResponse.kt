package com.example.banqu.weathernews.common

import org.simpleframework.xml.*

/**
 * Created by banqu on 2018/03/21.
 */

@Root(name = "rss", strict = false)
class LocationResponse {

    @set:Element(required = true)
    @get:Element(required = true)
    var channel = Channel()
}

@Root(strict = false)
class Channel {
    @set:Element
    @get:Element
    var title = ""
    @set:Element
    @get:Element
    var link = ""

    @Namespace(prefix = "ldWeather")
    @set:ElementList(required = true)
    @get:ElementList(required = true)
    var source = arrayListOf<Prefecture>()
}

class WeatherSource {

    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var title = ""

    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var link = ""

    @set:ElementList(required = true)
    @get:ElementList(required = true)
    var pref = arrayListOf<Prefecture>()
}

class Prefecture {
    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var title = ""

    @set:Element(required = true)
    @get:Element(required = true)
    var warn = Warning()

    @set:ElementList(inline = true, required = true)
    @get:ElementList(inline = true, required = true)
    var city = arrayListOf<City>()
}

class Warning {
    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var title = ""

    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var source = ""
}

class City {
    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var title = ""

    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var id = ""

    @set:Attribute(required = true)
    @get:Attribute(required = true)
    var source = ""
}