package com.anamarin.bitcoinpricesapp.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

@Dao
abstract class BitcoinInfoDao {

    @Query("SELECT * FROM bitcoin_table WHERE id = :timespan")
    abstract fun getLastBitcoinInfoSaved(timespan: String): BitcoinChart?

    @Transaction
    open fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart) {
        addOrReplaceBitcoinInfo(bitcoinInfoToSave.info)
        bitcoinInfoToSave.values.forEach { addOrReplaceBitcoinChartPoints(it) }
    }

    @Insert(onConflict = REPLACE)
    abstract fun addOrReplaceBitcoinInfo(bitcoinInfoModel: BitcoinInfoModel)

    @Insert(onConflict = REPLACE)
    abstract fun addOrReplaceBitcoinChartPoints(bitcoinCoordinatesModel: BitcoinCoordinatesModel)

    @Query("DELETE FROM bitcoin_table")
    abstract fun deleteBitcoinTable()
}