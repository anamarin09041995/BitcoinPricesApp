package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart

interface BitcoinInfoLocal {

    fun getLastBitcoinInfoSaved(timespan: String): BitcoinChart?

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart)
}