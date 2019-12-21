package com.anamarin.bitcoinpricesapp.core.utils

import com.github.mikephil.charting.components.AxisBase

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*


internal class DateAxisValueFormatter(period: String) : ValueFormatter() {
    var sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
    private val periodFormat = period

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        when(periodFormat){
            MONTHS_PERIOD -> sdf = SimpleDateFormat("d MMM")
            YEAR_PERIOD -> sdf = SimpleDateFormat("MMM. ''yy")
        }
        return sdf.format(Date((value.toLong()) * 1000))
    }

}