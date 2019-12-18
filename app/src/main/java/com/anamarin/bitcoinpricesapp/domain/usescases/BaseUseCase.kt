package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

abstract class BaseUseCase(
    private val repository: BitcoinInfoRepository
) {

    fun call(quantity: Int, period: String): Outcome<BitcoinInfoEntity> {
        val result = repository.fetchBitcoinInfo(quantity, period)
        return if (result is Success<*>) {
            val entity = BitcoinInfoEntity(result.data as BitcoinInfoModel)
            Success(entity)
        } else {
            Failure(Exception())
        }
    }
}