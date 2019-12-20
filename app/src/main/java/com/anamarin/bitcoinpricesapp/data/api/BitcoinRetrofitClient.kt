package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Single

interface BitcoinRetrofitClient {

    @GET("/charts/{name}")
    fun getBitcoinInfoForPeriod(@Path("name") name: String, @Query("timespan") timestamp: String,  @Query("format") format: String = "json"): Single<BitcoinInfoModel>

//    https://api.blockchain.info/charts/market-price?timespan=5weeks&format=json

}