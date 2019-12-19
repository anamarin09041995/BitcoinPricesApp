package com.anamarin.bitcoinpricesapp.data.local

import androidx.room.Dao
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

@Dao
abstract class BitcoinInfoDao {

    fun getLastBitcoinInfoSaved(): BitcoinInfoModel? {
        return  BitcoinInfoModel()
    }

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel) {}
}