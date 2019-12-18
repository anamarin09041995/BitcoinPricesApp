package com.anamarin.bitcoinpricesapp.core.utils

import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity

fun getTestBitcoinInfoModel(): BitcoinInfoModel {
    return BitcoinInfoModel(name = "market-prices", unit = "USD", values = (0..6).map {  BitcoinCoordinatesModel(it.toDouble(), it.toDouble()) })
}

fun getTestBitcoinInfoEntity(bitcoinInfoModel: BitcoinInfoModel): BitcoinInfoEntity{
    return  BitcoinInfoEntity(bitcoinInfoModel)
}