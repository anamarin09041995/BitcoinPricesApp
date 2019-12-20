package com.anamarin.bitcoinpricesapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
class BitcoinInfoModel(

    var id: Long? = 0,
    var status: String? = null,
    var name: String = "",
    var unit: String = "",
    var period: String? = null,
    var description: String? = null,

    var values: List<BitcoinCoordinatesModel> = listOf()
) : Parcelable


class BitcoinCoordinatesModelConverter {

    @TypeConverter
    fun listToJson(values: List<BitcoinCoordinatesModel>): String {
        return values.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun jsonToList(value: String): List<BitcoinCoordinatesModel> {
        val a = value.let { Gson().fromJson(it, Array<BitcoinCoordinatesModel>::class.java) as Array<BitcoinCoordinatesModel> }
        return  a.toList()
    }
}