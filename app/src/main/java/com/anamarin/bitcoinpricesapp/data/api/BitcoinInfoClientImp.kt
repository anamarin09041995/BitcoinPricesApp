package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import io.reactivex.Single
import javax.inject.Inject

class BitcoinInfoClientImp @Inject constructor(private val retrofit: BitcoinRetrofitClient): BitcoinInfoClient{

    override fun getBitcoinInfoInPeriodSingle(name: String, timestamp: String): Single<BitcoinInfoModel> {
        return retrofit.getBitcoinInfoForPeriod(name, timestamp)
    }

}