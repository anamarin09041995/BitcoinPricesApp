package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Results
import com.anamarin.bitcoinpricesapp.core.result.Results.Success
import com.anamarin.bitcoinpricesapp.core.result.Results.Failure
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

abstract class BaseUseCase(private val repository: BitcoinInfoRepository, private val quantity: Int, private val period: String){

    fun call(): Results<BitcoinInfoEntity> {
        val result = repository.fetchBitcoinInfo(quantity, period)
        return if (result is Success<*>) {
            val entity = BitcoinInfoEntity(result.data as BitcoinInfoModel)
            Success(entity)
        } else {
            Failure(Exception())
        }
    }
}