package com.idzayu.weathertest.repository

import com.google.gson.annotations.SerializedName
import java.util.Date

data class DateWeatherForecastModel(
    val now: Long,
    @SerializedName("now_dt") val nowDt: String,
    val info: Info,
    val fact: Fact,
    val forecasts: ArrayList<Forecast>
)
