package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

class BitcoinInfoLocalImp(private val local: BitcoinInfoDao): BitcoinInfoLocal{

    override fun getLastBitcoinInfoSaved(): BitcoinInfoModel? {
        return local.getLastBitcoinInfoSaved()
    }

    override fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel) {
        return local.saveBitcoinInfo(bitcoinInfoToSave)
    }

}