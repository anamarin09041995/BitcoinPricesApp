package com.anamarin.bitcoinpricesapp.domain.repositories

import com.anamarin.bitcoinpricesapp.core.result.Outcome

interface BitcoinInfoRepository {

    fun fetchBitcoinInfo(quantity: Int, period: String): Outcome<*>

}