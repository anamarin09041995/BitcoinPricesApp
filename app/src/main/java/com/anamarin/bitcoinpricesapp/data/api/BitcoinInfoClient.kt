package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

interface BitcoinInfoClient {

    fun getBitcoinInfoInPeriod(timestan: String): Outcome<BitcoinInfoModel>
}