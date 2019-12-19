package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class BitcoinCoordinatesModel(
    @PrimaryKey
    val id: Long,
    var x: Double,
    var y: Double
)

