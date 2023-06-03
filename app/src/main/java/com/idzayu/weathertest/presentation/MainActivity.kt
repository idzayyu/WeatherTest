package com.idzayu.weathertest.presentation


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.idzayu.weathertest.R
import com.idzayu.weathertest.adapters.ForecastDayAdapter
import com.idzayu.weathertest.databinding.ActivityMainBinding
import com.idzayu.weathertest.repository.ForecastList
import java.util.*

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private var presenter: MainPresenter = MainPresenterImpl(this)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locality: String

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {

                        val geocoder = Geocoder(this@MainActivity, Locale("ru"))
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1,
                            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                            object : Geocoder.GeocodeListener {
                                override fun onGeocode(addresses: MutableList<Address>) {

                                    locality = addresses[0].locality
                                }

                                override fun onError(errorMessage: String?) {
                                    super.onError(errorMessage)

                                }

                            })

                        presenter.load(
                            "forecast",
                            location.latitude.toString(),
                            location.longitude.toString()
                        )

                    }
                }
            } else {

                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            locality = "Москва"
            presenter.load("forecast", "55.75396", "37.620393")
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation
        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }


    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun showForecast() {
        binding.apply {

            if (ForecastList.forecastDayFavoriteList.isNotEmpty()) {

                binding.factTemp.text =
                    ForecastList.forecastDayFavoriteList[0].fact.temp.toString() + " С°"

                binding.tempDayNight.text =
                    ForecastList.forecastDayFavoriteList[0].forecasts[0].parts.day.tempAvg.toString() + "° / " + ForecastList.forecastDayFavoriteList[0].forecasts[0].parts.night.tempAvg.toString() + "°"


                when (ForecastList.forecastDayFavoriteList[0].fact.condition) {
                    "clear" -> loadImageWeather(1)
                    "partly-cloudy" -> loadImageWeather(2)
                    "cloudy" -> loadImageWeather(2)
                    "overcast" -> loadImageWeather(3)
                    "drizzle" -> loadImageWeather(4)
                    "light-rain" -> loadImageWeather(5)
                    "rain" -> loadImageWeather(5)
                    "moderate-rain" -> loadImageWeather(6)
                    "heavy-rain" -> loadImageWeather(6)
                    "continuous-heavy-rain" -> loadImageWeather(6)
                    "showers" -> loadImageWeather(6)
                    "wet-snow" -> loadImageWeather(7)
                    "light-snow" -> loadImageWeather(7)
                    "snow" -> loadImageWeather(7)
                    "snow-showers" -> loadImageWeather(8)
                    "hail" -> loadImageWeather(9)
                    "thunderstorm" -> loadImageWeather(10)
                    "thunderstorm-with-rain" -> loadImageWeather(11)
                    "thunderstorm-with-hail" -> loadImageWeather(9)
                    else -> {
                        Glide.with(artMovie)
                            .load(android.R.drawable.stat_notify_error)
                            .into(artMovie)
                    }
                }


                binding.toolbarTitle.text = locality
                binding.rw.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rw.adapter =
                    ForecastDayAdapter(ForecastList.forecastDayFavoriteList[0].forecasts)

            }
        }
    }

    private fun loadImageWeather(int: Int) {
        when (int) {
            1 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.sunny_sunshine_weather)
                    .into(binding.artMovie)
            2 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.sunny_sun_cloud_time_time)
                    .into(binding.artMovie)
            3 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.overcast_cloudy_cloud_weather)
                    .into(binding.artMovie)
            4 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.fog_weather)
                    .into(binding.artMovie)
            5 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.weather_showers_lightrain_cloud_clim)
                    .into(binding.artMovie)
            6 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.weather_showers_rain_cloud)
                    .into(binding.artMovie)
            7 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.littlesnow_cloud_weather_pocanieve)
                    .into(binding.artMovie)
            8 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.snow_cloud_weather)
                    .into(binding.artMovie)
            9 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.hail_cloud_weather)
                    .into(binding.artMovie)
            10 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.lightning_weather_storm)
                    .into(binding.artMovie)
            11 ->
                Glide.with(binding.artMovie)
                    .load(R.drawable.weather_storms_storm_rain_thunder)
                    .into(binding.artMovie)
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                 getLastLocation()
            }
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            REQUEST_CODE_PERMISSIONS
        )
    }

    companion object {

        private const val REQUEST_CODE_PERMISSIONS = 10

        // TODO("Указать набор требуемых разрешений")
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION

        ).toTypedArray()

    }
}

