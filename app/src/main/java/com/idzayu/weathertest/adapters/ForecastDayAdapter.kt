package com.idzayu.weathertest.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idzayu.weathertest.R
import com.idzayu.weathertest.databinding.DayFarecastBinding
import com.idzayu.weathertest.repository.Forecast
import com.idzayu.weathertest.repository.ForecastList

class ForecastDayAdapter(private val partList: List<Forecast>
) : RecyclerView.Adapter<ForecastDayAdapter.ForecastDayHolder>() {



    inner class ForecastDayHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = DayFarecastBinding.bind(item)


        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(
            forecastDay: Forecast
        ) = with(binding) {

            day.text = forecastDay.date
            tempNightAvg.text = forecastDay.parts.day.tempAvg.toString() + "° / " + forecastDay.parts.night.tempAvg.toString() + "°"

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_farecast, parent, false)
        return ForecastDayHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastDayHolder, position: Int) {
        if (partList.isNotEmpty()){
            holder.bind(partList[position])
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }


}