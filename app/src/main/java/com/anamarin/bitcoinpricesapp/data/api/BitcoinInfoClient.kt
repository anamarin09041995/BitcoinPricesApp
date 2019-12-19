package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import io.reactivex.Single
interface BitcoinInfoClient {

    fun getBitcoinInfoInPeriodSingle(name: String, timestamp: String): Single<BitcoinInfoModel>

}