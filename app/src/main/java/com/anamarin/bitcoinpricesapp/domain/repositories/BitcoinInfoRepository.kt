package com.anamarin.bitcoinpricesapp.domain.repositories

import com.anamarin.bitcoinpricesapp.core.result.Results
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel

interface BitcoinInfoRepository {

    fun fetchBitcoinInfo(quantity: Int, period: String): Results<*>

}