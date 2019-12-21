package com.anamarin.bitcoinpricesapp.core.utils

import com.anamarin.bitcoinpricesapp.data.api.BitcoinChartDTO
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity

fun getBitcoinInfoModelTest(): BitcoinInfoModel {
    return BitcoinInfoModel(
        name = "market-prices",
        unit = "USD"
    )
}

fun getListBitcoinCoordinatesModelTest(size: Int = 5, bitcoinInfoId: String = ""): List<BitcoinCoordinatesModel> {
    return (0..size).map { BitcoinCoordinatesModel(0, bitcoinInfoId, it.toDouble(), it.toDouble()) }
}

fun getBitcoinInfoDTOTest(): BitcoinChartDTO {
    return BitcoinChartDTO(
        name = "market-prices",
        unit = "USD",
        values = getListBitcoinCoordinatesModelTest()
    )
}

fun getBitcoinEntityTest(): BitcoinInfoEntity {
    return BitcoinInfoEntity(BitcoinChart(getBitcoinInfoModelTest(), getListBitcoinCoordinatesModelTest()))
}