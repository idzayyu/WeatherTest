package com.idzayu.weathertest.presentation

import com.idzayu.weathertest.repository.DateWeatherForecastModel

interface MainPresenter {
    fun load(name: String,lat: String, lon: String)
    fun save()
}