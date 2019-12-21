package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize

@Entity(
    foreignKeys = [ForeignKey(
        entity = BitcoinInfoModel::class, parentColumns = arrayOf("id"),
        childColumns = arrayOf("bitcoinInfoId"),
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )]
)
data class BitcoinCoordinatesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var bitcoinInfoId: String, // foreign key
    val x: Double,
    val y: Double
) : Parcelable

