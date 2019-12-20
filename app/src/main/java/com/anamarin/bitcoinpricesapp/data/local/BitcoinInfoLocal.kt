package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart

interface BitcoinInfoLocal {

    fun getLastBitcoinInfoSaved(): BitcoinChart?

    fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart)
}