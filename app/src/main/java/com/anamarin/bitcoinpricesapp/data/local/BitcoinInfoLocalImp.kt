package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import javax.inject.Inject

class BitcoinInfoLocalImp @Inject constructor(private val dao: BitcoinInfoDao): BitcoinInfoLocal{

    override fun getLastBitcoinInfoSaved(timespan: String): BitcoinChart? {
        return dao.getLastBitcoinInfoSaved(timespan)
    }

    override fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart) {
        return dao.saveBitcoinInfo(bitcoinInfoToSave)
    }

}