package com.anamarin.bitcoinpricesapp.domain.entities

import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

class BitcoinInfoEntity(bitcoinInfoModel: BitcoinInfoModel) {

    val name: String = bitcoinInfoModel.name
    val unit: String = bitcoinInfoModel.unit
    val values: List<BitcoinCoordinatesModel> = bitcoinInfoModel.values

    override fun equals(other: Any?): Boolean {
        val otherEntity = other as BitcoinInfoEntity
        if(this.name == otherEntity.name && this.unit == otherEntity.unit && values == otherEntity.values ) {
            return true
        }
        return false
    }
}