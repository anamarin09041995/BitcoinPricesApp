package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

interface BitcoinInfoDao {

    fun getLastBitcoinInfoSaved(): Outcome<*>{
        TODO()
    }

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel){
        TODO()
    }
}