package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

interface BitcoinInfoDao {

    fun getLastBitcoinInfoSaved(): BitcoinInfoModel?

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel)
}