package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

// Api response
class BitcoinChartDTO(
    status: String? = null,
    name: String,
    unit: String ,
    period: String? = null,
    description: String? = null,
    val values: List<BitcoinCoordinatesModel>
) : BitcoinInfoModel(status = status, name = name, unit = unit, period = period, description = description)