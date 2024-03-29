package com.anamarin.bitcoinpricesapp.data.api

import io.reactivex.Single

interface BitcoinInfoClient {

    fun getBitcoinInfoInPeriodSingle(name: String, timespan: String): Single<BitcoinChartDTO>

}