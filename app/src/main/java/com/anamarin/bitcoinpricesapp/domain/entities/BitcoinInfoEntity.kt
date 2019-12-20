package com.anamarin.bitcoinpricesapp.domain.entities

import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel

class BitcoinInfoEntity(bitcoinChart: BitcoinChart) {

    private val name: String = bitcoinChart.info.name
    private val unit: String = bitcoinChart.info.unit
    private val values: List<BitcoinCoordinatesModel> = bitcoinChart.values

    override fun equals(other: Any?): Boolean {
        val otherEntity = other as BitcoinInfoEntity
        if(this.name == otherEntity.name && this.unit == otherEntity.unit && values == otherEntity.values ) {
            return true
        }
        return false
    }
}