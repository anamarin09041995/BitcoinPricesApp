package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.error.DatabaseException
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoLocal
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class BitcoinInfoRepositoryImp @Inject constructor(
    private val localData: BitcoinInfoLocal,
    private val remoteData: BitcoinInfoClient,
    private val networkStatus: NetworkStatus
) : BitcoinInfoRepository {

    override fun fetchBitcoinInfoSingle(
        quantity: Int,
        period: String,
        name: String
    ): Single<Outcome<BitcoinInfoModel>> {
        val quantityString: String = quantity.toString()
        val timestamp: String = quantityString + period

        return if (networkStatus.hasNetworkAccess()) {
            remoteData.getBitcoinInfoInPeriodSingle(name, timestamp)
                /*.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*/
                .map {
                    localData.saveBitcoinInfo(it)
                    Success(it) as Outcome<BitcoinInfoModel>
                }
                .onErrorResumeNext {
                    Single.fromCallable { getLastBitcoinSaved() }
                }
        } else {
            Single.fromCallable { getLastBitcoinSaved() }
        }
    }


    fun getLastBitcoinSaved(): Outcome<BitcoinInfoModel> {
        val lastBitcoinSaved = localData.getLastBitcoinInfoSaved()

        return if (lastBitcoinSaved != null) {
            Success(lastBitcoinSaved)
        } else {
            Failure(DatabaseException())
        }
    }

}