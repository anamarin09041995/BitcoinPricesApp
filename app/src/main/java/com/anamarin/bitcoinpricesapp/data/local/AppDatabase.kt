package com.anamarin.bitcoinpricesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

@Database(entities = arrayOf(BitcoinCoordinatesModel::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun bitcoinInfoDao(): BitcoinInfoDao
}