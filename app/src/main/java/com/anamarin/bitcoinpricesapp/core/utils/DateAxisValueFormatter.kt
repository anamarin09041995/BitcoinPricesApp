package com.anamarin.bitcoinpricesapp.core.utils

import com.github.mikephil.charting.components.AxisBase

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*


internal class DateAxisValueFormatter : ValueFormatter() {
    var sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")


    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return sdf.format(Date((value.toLong()) * 1000))
    }

}