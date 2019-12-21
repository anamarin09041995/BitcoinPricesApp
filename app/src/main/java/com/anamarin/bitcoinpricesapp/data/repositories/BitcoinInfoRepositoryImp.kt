package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.error.DatabaseException
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoLocal
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
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
    ): Single<Outcome<BitcoinChart>> {
        val quantityString: String = quantity.toString()
        val timespan: String = quantityString + period

        return if (networkStatus.hasNetworkAccess()) {
            remoteData.getBitcoinInfoInPeriodSingle(name, timespan)
                .map {
                    val bitcoinChart = BitcoinChart(it, it.values)
                    bitcoinChart.info.timespan = timespan
                    bitcoinChart.values.forEach { it.bitcoinInfoId = timespan }
                    localData.saveBitcoinInfo(bitcoinChart)
                    Success(bitcoinChart) as Outcome<BitcoinChart>
                }
                .onErrorResumeNext {
                    Single.fromCallable { getLastBitcoinSaved(timespan) }
                }
        } else {
            Single.fromCallable { getLastBitcoinSaved(timespan) }
        }
    }


    private fun getLastBitcoinSaved(timespan: String): Outcome<BitcoinChart> {
        val lastBitcoinSaved = localData.getLastBitcoinInfoSaved(timespan)

        return if (lastBitcoinSaved != null) {
            Success(lastBitcoinSaved)
        } else {
            Failure(DatabaseException())
        }
    }

}