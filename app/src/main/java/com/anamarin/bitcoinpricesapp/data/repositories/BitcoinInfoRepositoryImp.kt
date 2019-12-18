package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Outcome.Success
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoDao
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

class BitcoinInfoRepositoryImp(private val localData: BitcoinInfoDao, private val remoteData: BitcoinInfoClient, private val networkStatus: NetworkStatus): BitcoinInfoRepository{

    override fun fetchBitcoinInfo(quantity: Int, period: String): Outcome<*> {
        networkStatus.hasNetworkAccess()
        return Success(localData.getLastBitcoinInfoSaved())
    }

}