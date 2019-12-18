package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.result.Results
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

class BitcoinInfoRepositoryImp: BitcoinInfoRepository{

    override fun fetchBitcoinInfo(quantity: Int, period: String): Results<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}