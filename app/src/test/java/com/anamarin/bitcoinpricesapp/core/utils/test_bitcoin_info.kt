package com.anamarin.bitcoinpricesapp.core.utils

import com.anamarin.bitcoinpricesapp.data.api.BitcoinChartDTO
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoDao
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

fun getBitcoinInfoModelTest(): BitcoinInfoModel {
    return BitcoinInfoModel(
        name = "market-prices",
        unit = "USD"
    )
}

fun getListBitcoinCoordinatesModelTest(size: Int = 5, bitcoinInfoId: Long = 0): List<BitcoinCoordinatesModel> {
    return (0..size).map { BitcoinCoordinatesModel(bitcoinInfoId, bitcoinInfoId, it.toDouble(), it.toDouble()) }
}

fun getBitcoinInfoDTOTest(): BitcoinChartDTO {
    return BitcoinChartDTO(
        name = "market-prices",
        unit = "USD",
        values = getListBitcoinCoordinatesModelTest()
    )
}


//
//fun getTestBitcoinChart(bitcoinInfoModel: BitcoinInfoModel): BitcoinChart {
//    return BitcoinChart(bitcoinInfoModel)
//}