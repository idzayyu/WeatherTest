package com.idzayu.weathertest.repository

import android.app.Application
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AppWeather: Application() {

    lateinit var api: DateWeatherForecastApi

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClient.Builder()

        client.addInterceptor{ chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .addHeader("X-Yandex-API-Key", API_KEY)
                .build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        api = retrofit.create(DateWeatherForecastApi::class.java)
    }

    companion object{
        const val BASE_URL = "https://api.weather.yandex.ru/"
        const val API_KEY = "6cbe74df-6e52-4ba0-8f14-e221b9b6919e"
        lateinit var instance: AppWeather
            private set
    }
}