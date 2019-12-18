package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

class GetBitcoinInfoUsecase(repository: BitcoinInfoRepository, quantity: Int, period: String): BaseUseCase(repository = repository, quantity = quantity, period = period ){

    /*fun call(): Results<BitcoinInfoEntity> {
        val result = repository.fetchBitcoinInfo(quantity, WEEK_PERIOD)
        return if (result is Success<*>) {
            val entity = BitcoinInfoEntity(result.data as BitcoinInfoModel)
            Success(entity)
        } else {
                Failure(Exception())
        }
    }*/
}