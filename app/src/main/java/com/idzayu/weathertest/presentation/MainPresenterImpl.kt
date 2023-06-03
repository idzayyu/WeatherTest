package com.idzayu.weathertest.presentation

import android.app.Activity
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.idzayu.weathertest.repository.ApiForecastRepository
import com.idzayu.weathertest.repository.DateWeatherForecastModel
import com.idzayu.weathertest.repository.ForecastList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MainPresenterImpl(
    private val view: MainView
): MainPresenter {


    private val movieRepository : ApiForecastRepository = ApiForecastRepository()
    override fun load(name: String,lat: String, lon: String) {
        movieRepository.getForecast(name,lat,lon, object : ApiForecastRepository.MyCallback{
            override fun onSuccess(result: DateWeatherForecastModel) {
                ForecastList.forecastDayFavoriteList.add(result)
                view.showForecast()
            }

            override fun onFailure(error: Throwable) {
                Log.d("ForecastList",error.message, error)

            }

        })
    }

    override fun save() {
        TODO("Not yet implemented")
    }

}