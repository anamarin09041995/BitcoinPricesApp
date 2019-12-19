package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

interface BitcoinInfoLocal {

    fun getLastBitcoinInfoSaved(): BitcoinInfoModel?

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel)
}