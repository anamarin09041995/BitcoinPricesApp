package com.anamarin.bitcoinpricesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

@Dao
abstract class BitcoinInfoDao {

    @Query("SELECT * FROM bitcoin_table LIMIT 1")
    abstract fun getLastBitcoinInfoSaved(): BitcoinChart?

    @Transaction
    open fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart) {
        addOrReplaceBitcoinInfo(bitcoinInfoToSave.info)
        bitcoinInfoToSave.values.forEach { addOrReplaceBitcoinChartPoints(it) }
    }

    @Insert(onConflict = REPLACE)
    abstract fun addOrReplaceBitcoinInfo(bitcoinInfoModel: BitcoinInfoModel)

    @Insert(onConflict = REPLACE)
    abstract fun addOrReplaceBitcoinChartPoints(bitcoinCoordinatesModel: BitcoinCoordinatesModel)

}