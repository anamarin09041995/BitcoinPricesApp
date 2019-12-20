package com.anamarin.bitcoinpricesapp.data.local

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import javax.inject.Inject

class BitcoinInfoLocalImp @Inject constructor(private val dao: BitcoinInfoDao): BitcoinInfoLocal{

    override fun getLastBitcoinInfoSaved(): BitcoinInfoModel? {
        return dao.getLastBitcoinInfoSaved()
    }

    override fun saveBitcoinInfo(bitcoinInfoToSave: BitcoinInfoModel) {
        return dao.saveBitcoinInfo(bitcoinInfoToSave)
    }

}