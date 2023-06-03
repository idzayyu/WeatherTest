package com.idzayu.weathertest.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DateWeatherForecastApi {
    @GET("v2/{name}")
    fun getCommitsByName(
         @Path("name") name: String
        ,@Query("lat") lat: String
        ,@Query("lon") lon: String
         ,@Query("lang") lang: String = "ru_RU"): Call<DateWeatherForecastModel>
}