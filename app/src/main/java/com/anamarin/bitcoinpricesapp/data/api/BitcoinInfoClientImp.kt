package com.anamarin.bitcoinpricesapp.data.api

import io.reactivex.Single
import javax.inject.Inject

class BitcoinInfoClientImp @Inject constructor(private val retrofit: BitcoinRetrofitClient): BitcoinInfoClient{

    override fun getBitcoinInfoInPeriodSingle(name: String, timestamp: String): Single<BitcoinChartDTO> {
        return retrofit.getBitcoinInfoForPeriod(name, timestamp)
    }

}