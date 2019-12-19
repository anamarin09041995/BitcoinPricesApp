package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import io.reactivex.Single

abstract class BaseUseCase(private val repository: BitcoinInfoRepository) {

    fun callSingle(quantity: Int, period: String, name: String): Single<Outcome<BitcoinInfoEntity>>{
        return repository.fetchBitcoinInfoSingle(quantity, period, name)
                .map {
                    Success(BitcoinInfoEntity((it as Success).data)) as Outcome<BitcoinInfoEntity>
                }.onErrorResumeNext {
                Single.fromCallable { Failure(Exception()) }
            }
    }
}