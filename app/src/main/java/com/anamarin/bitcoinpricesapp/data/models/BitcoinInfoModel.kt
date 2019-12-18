package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class BitcoinInfoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var status: String? = null,
    var name: String,
    var unit: String,
    var period: String? = null,
    var description: String? = null,
    var values: List<BitcoinCoordinatesModel>
): Parcelable