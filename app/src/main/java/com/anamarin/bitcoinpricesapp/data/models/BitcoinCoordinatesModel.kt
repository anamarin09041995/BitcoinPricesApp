package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BitcoinCoordinatesModel(
    var x: Double,
    var y: Double
) : Parcelable