package com.anamarin.bitcoinpricesapp.data.models

data class BitcoinInfoModel(
    var status: String? = null,
    var name: String,
    var unit: String,
    var period: String? = null,
    var description: String? = null,
    var values: List<BitcoinCoordinatesModel>
)