package com.anamarin.bitcoinpricesapp.domain.repositories

import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import io.reactivex.Single

interface BitcoinInfoRepository {

    fun fetchBitcoinInfo(): Single<BitcoinInfoEntity>

}