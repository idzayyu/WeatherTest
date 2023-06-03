package com.idzayu.weathertest.repository

import com.google.gson.annotations.SerializedName

data class Info(
    val lat: Double,
    val lon: Double,
    val url: String
)

data class Fact(
    val temp: Int,
    @SerializedName("feels_like") val feelsLike: Int,
    val icon: String,
    val condition: String,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mm") val pressureMm: Int,
    @SerializedName("pressure_pa") val pressurePa: Int,
    val humidity: Int,
    val daytime: String,
    val polar: Boolean,
    val season: String,
    @SerializedName("obs_time") val obsTime: Long
)

data class Forecast(
    val date: String,
    val dateTs: Int,
    val week: Int,
    val sunrise: String,
    val sunset: String,
    @SerializedName("moon_code") val moonCode: Int,
    @SerializedName("moon_text") val moonText: String,
    @SerializedName("parts") val parts: Parts,
    val hours: ArrayList<Hour>
)

data class Parts(
    @SerializedName("day") val day: Day,
    @SerializedName("night") val night: Night,
)

data class Day(
    @SerializedName("part_name") val partName: String,
    @SerializedName("temp_min") val tempMin: Int,
    @SerializedName("temp_max") val tempMax: Int,
    @SerializedName("temp_avg") val tempAvg: Int,
    @SerializedName("feels_like") val feelsLike: Int,
    val icon: String,
    val condition: String,
    val daytime: String,
    val polar: Boolean,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mm") val pressureMm: Int,
    @SerializedName("pressure_pa") val pressurePa: Int,
    val humidity: Double,
    @SerializedName("prec_mm") val precMm: Double,
    @SerializedName("prec_period") val precPeriod: Double,
    @SerializedName("prec_prob") val precProb: Double
)

data class Night(
    @SerializedName("part_name") val partName: String,
    @SerializedName("temp_min") val tempMin: Int,
    @SerializedName("temp_max") val tempMax: Int,
    @SerializedName("temp_avg") val tempAvg: Int,
    @SerializedName("feels_like") val feelsLike: Int,
    val icon: String,
    val condition: String,
    val daytime: String,
    val polar: Boolean,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mm") val pressureMm: Int,
    @SerializedName("pressure_pa") val pressurePa: Int,
    val humidity: Double,
    @SerializedName("prec_mm") val precMm: Double,
    @SerializedName("prec_period") val precPeriod: Double,
    @SerializedName("prec_prob") val precProb: Double
)

data class Hour(
    val hour: Int,
    @SerializedName("hour_ts") val hourTs: Int,
    @SerializedName("temp") val tempMin: Double,
    @SerializedName("feels_like") val feelsLike: Int,
    val icon: String,
    val condition: String,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mm") val pressureMm: Int,
    @SerializedName("pressure_pa") val pressurePa: Int,
    val humidity: Double,
    @SerializedName("prec_mm") val precMm: Double,
    @SerializedName("prec_period") val precPeriod: Double,
    @SerializedName("prec_prob") val precProb: Double,
    @SerializedName("prec_strength") val precStrength: Double,
    @SerializedName("is_thunder") val isThunder: Boolean,
    val cloudness: Double
)








