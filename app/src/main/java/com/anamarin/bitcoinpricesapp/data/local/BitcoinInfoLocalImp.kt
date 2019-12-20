package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import javax.inject.Inject

class BitcoinInfoLocalImp @Inject constructor(private val local: BitcoinInfoDao): BitcoinInfoLocal{

    override fun getLastBitcoinInfoSaved(): BitcoinInfoModel? {
        return local.getLastBitcoinInfoSaved()
    }

    override fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel) {
        return local.saveBitcoinInfo(bitcoinInfoToSave)
    }

}