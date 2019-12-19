package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import io.reactivex.Single

class BitcoinInfoClientImp (private val retrofit: BitcoinRetrofitClient): BitcoinInfoClient{

    override fun getBitcoinInfoInPeriodSingle(name: String, timestamp: String): Single<BitcoinInfoModel> {
        return retrofit.getBitcoinInfoForPeriod(name, timestamp)
    }

}