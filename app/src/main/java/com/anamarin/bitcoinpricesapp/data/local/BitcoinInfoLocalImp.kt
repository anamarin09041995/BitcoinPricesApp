package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import javax.inject.Inject

class BitcoinInfoLocalImp @Inject constructor(private val dao: BitcoinInfoDao): BitcoinInfoLocal{

    override fun getLastBitcoinInfoSaved(): BitcoinChart? {
        return dao.getLastBitcoinInfoSaved()
    }

    override fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinChart) {
        return dao.saveBitcoinInfo(bitcoinInfoToSave)
    }

}