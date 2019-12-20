package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import io.reactivex.Single

abstract class BaseUseCase(protected val repository: BitcoinInfoRepository) {

    abstract fun callSingle(quantity: Int, period: String, name: String): Single<Outcome<BitcoinInfoEntity>>
}