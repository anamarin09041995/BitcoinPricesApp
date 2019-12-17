package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

class GetBitcoinInfoOfLastWeek(private val repository: BitcoinInfoRepository) {

    private val quantity: Int = 1

    fun call() {
        repository.fetchBitcoinInfo(quantity, WEEK_PERIOD) //To change body of created functions use File | Settings | File Templates.
    }

}