package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.error.ServerException
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoDao
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository

class BitcoinInfoRepositoryImp(
    private val localData: BitcoinInfoDao,
    private val remoteData: BitcoinInfoClient,
    private val networkStatus: NetworkStatus
) : BitcoinInfoRepository {

    override fun fetchBitcoinInfo(quantity: Int, period: String): Outcome<BitcoinInfoModel> {

        val quantityString: String = quantity.toString()
        val timestamp: String = quantityString + period

        return if (networkStatus.hasNetworkAccess()) {
            val result = remoteData.getBitcoinInfoInPeriod(timestamp)
            if (result is Success){
                localData.saveBitcoinInfo(result.data)
                Success(result.data)
            }else{
                Success(localData.getLastBitcoinInfoSaved())
                //Failure(ServerException())
            }
        } else {
            //Get local data when offline
            Success(localData.getLastBitcoinInfoSaved())
        }
    }

    /*fun getLocalData(): Outcome<BitcoinInfoModel>{
        val localBitcoinInfo = localData.getLastBitcoinInfoSaved()
        if(localBitcoinInfo)
    }*/

}