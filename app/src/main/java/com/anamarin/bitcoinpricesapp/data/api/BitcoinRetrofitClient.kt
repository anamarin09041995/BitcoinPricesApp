package com.anamarin.bitcoinpricesapp.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BitcoinRetrofitClient {

    @GET("/charts/{name}")
    fun getBitcoinInfoForPeriod(@Path("name") name: String, @Query("timespan") timestamp: String,  @Query("format") format: String = "json"): Single<BitcoinChartDTO>

//    https://api.blockchain.info/charts/market-price?timespan=5weeks&format=json

}