package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BitcoinInfoClient {

    fun getBitcoinInfoInPeriod(timestan: String): Outcome<BitcoinInfoModel>

    fun getBitcoinInfoInPeriodSingle(name: String, timestamp: String): Single<BitcoinInfoModel>

    /*@GET("/charts/{name}")
    fun getBitcoinInfoInPeriodSingle(@Path("name") name:String, @Query("timespan") timestamp: String): Single<BitcoinInfoModel>*/
}