package com.example.a206712_chenhaojie_izwan_lab01.data


data class WeatherResponse(

    val current: CurrentWeather
)

data class CurrentWeather(

    val temperature_2m: Double,

    val relative_humidity_2m: Int
)