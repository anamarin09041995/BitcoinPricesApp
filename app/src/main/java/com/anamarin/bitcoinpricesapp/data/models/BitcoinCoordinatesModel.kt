package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class BitcoinCoordinatesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val bitcoinInfoId: Long, // foreign key
    val x: Double,
    val y: Double
) : Parcelable

