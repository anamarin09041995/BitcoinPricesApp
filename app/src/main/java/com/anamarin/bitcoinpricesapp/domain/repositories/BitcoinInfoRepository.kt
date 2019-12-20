package com.anamarin.bitcoinpricesapp.domain.repositories

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import io.reactivex.Single

interface BitcoinInfoRepository {

    fun fetchBitcoinInfoSingle(
        quantity: Int,
        period: String,
        name: String
    ): Single<Outcome<BitcoinChart>>

}