package com.anamarin.bitcoinpricesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

@Database(entities = arrayOf(BitcoinInfoModel::class, BitcoinCoordinatesModel::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun bitcoinInfoDao(): BitcoinInfoDao
}