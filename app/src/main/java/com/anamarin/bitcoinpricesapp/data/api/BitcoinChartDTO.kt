package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

class BitcoinChartDTO(
    status: String,
    name: String,
    unit: String ,
    period: String,
    description: String,
    val values: List<BitcoinCoordinatesModel>
) : BitcoinInfoModel(status = status, name = name, unit = unit, period = period, description = description)