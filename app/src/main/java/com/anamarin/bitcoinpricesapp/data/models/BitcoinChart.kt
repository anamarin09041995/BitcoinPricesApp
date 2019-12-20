package com.anamarin.bitcoinpricesapp.data.models

import androidx.room.Embedded
import androidx.room.Relation

class BitcoinChart(

    @Embedded
    val info: BitcoinInfoModel,

    @Relation(parentColumn = "id", entityColumn = "bitcoinInfoId")
    val values: List<BitcoinCoordinatesModel>

)