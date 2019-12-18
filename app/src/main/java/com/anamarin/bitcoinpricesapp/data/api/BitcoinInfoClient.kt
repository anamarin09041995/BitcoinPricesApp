package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.core.result.Outcome

interface BitcoinInfoClient {

    fun getBitcoinInfoInPeriod(timestan: String): Outcome<*>{
        TODO()
    }
}