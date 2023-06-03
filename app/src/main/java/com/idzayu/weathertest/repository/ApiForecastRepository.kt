package com.idzayu.weathertest.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import kotlin.coroutines.coroutineContext

class ApiForecastRepository{

    fun getForecast(name: String,lat: String, lon: String,callback: MyCallback) {
        getForecastList(name, lat,lon,callback)
    }

    private fun getForecastList(name: String, lat: String, lon: String,callback: MyCallback){

        AppWeather.instance.api.getCommitsByName(name,lat, lon)
            .enqueue(object : Callback<DateWeatherForecastModel> {
                override fun onResponse(
                    call: Call<DateWeatherForecastModel>,
                    response: Response<DateWeatherForecastModel>
                ) {

                    Log.d("ForecastList", "1Response: " + response.isSuccessful)
                    Log.d("ForecastList", "1Response: " + response.body())
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it)
                        }?.let { }
                    }
                }
                override fun onFailure(call: Call<DateWeatherForecastModel>, t: Throwable) {
                    Log.d("ForecastList", "Response: " +  t.message)

                    callback.onFailure(t)
                    return
                }
            })
    }
    interface MyCallback {
        fun onSuccess(result: DateWeatherForecastModel)
        fun onFailure(error: Throwable)
    }
}