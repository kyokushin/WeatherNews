package com.example.banqu.weathernews.api

/**
 * Created by banqu on 2018/03/19.
 */
class WeatherResponse {
    var publicTime = ""
    var title = ""
    var link = ""
    var description = Description()
    var forecasts = arrayListOf<Forecast>()
    var location = Location()
    var pinpontLocations = arrayListOf<PinpontLocation>()
    var copyright = Copyright()
}

class Description {
    var text = ""
    var publicTIme = ""
}

class Forecast {
    var dateLabel = ""
    var telop = ""
    var date = ""
    var temperature = TemperatureMinMax()
    var image = Image()
}

class TemperatureMinMax {
    var min: Temperature? = null
    var max: Temperature? = null
}

class Temperature {
    var celsius = ""
    var fahrenheit = ""
}

class Image {
    var width = -1
    var height = -1
    var url = ""
    var title = ""
}

class Location {
    var city = ""
    var area = ""
    var prefecture = ""
}

class PinpontLocation {
    var link = ""
    var name = ""
}

class Copyright {
    var provider = arrayListOf<Provider>()
    var link = ""
    var title = ""
    var image = Image()
}

class Provider {
    var link = ""
    var name = ""
}

